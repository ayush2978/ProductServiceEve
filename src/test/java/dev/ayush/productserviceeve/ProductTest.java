package dev.ayush.productserviceeve;

import dev.ayush.productserviceeve.models.Category;
import dev.ayush.productserviceeve.models.Product;
import dev.ayush.productserviceeve.repositories.CategoryRepository;
import dev.ayush.productserviceeve.repositories.ProductRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Array;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void savingProductAndCategory(){
        // If you dont save the category, the product will not get saved.
//        Category category=new Category();
//        category.setName("Grocery");
//        category.setDescription("Groceries and their details");
////        categoryRepository.save(category);
//
//        Product product=new Product();
//        product.setTitle("Rice");
//        product.setPrice(89999);
//        product.setDescription("Basmati");
//        product.setCategory(category);
//        product.setImageUrl("https://m.media-amazon.com/images/I/61n6Ovq6EdL._AC_SL1500_.jpg");
//        productRepository.save(product);

        Category category=new Category();
        category.setName("Electronics");
        category.setDescription("Electronics and their details");

        Product product=new Product();
        product.setTitle("Laptop");
        product.setPrice(89999);
        product.setDescription("Macbook");
        product.setCategory(category);
        product.setImageUrl("https://m.media-amazon.com/images/I/61n6Ovq6EdL._AC_SL1500_.jpg");
        productRepository.save(product);
    }

    @Test
    void deleteProduct(){
        productRepository.deleteById(1L); // Because it is CasCadeType=Remove, so it will first remove the category relation and then it will remove product
    }

    @Test
    void deleteCategory(){
        categoryRepository.deleteById(2L); // Because it is CasCadeType=Remove, so it will first remove the category relation and then it will remove product
    }

    @Test
    @Transactional // ORM creates a Begin transaction statement here.
        // All the DB queries executed in this method will be executed as a part
        // of this Transaction.
    // Your ORM will lazy load an object only if it is a part of the same transaction.
    // If not a part of the same transaction, we will get an exception because if we dont mention @Transactional, a session is created for each DB call and which makes this not work fine.
    void fetchTypesTest(){
        Product product=productRepository.findProductById(1L);
        System.out.println(product.getTitle());
        Category category=product.getCategory();
        System.out.println(category.getName());
    }

    @Test
    @Transactional // ALl the queries starting from 1st line of the method to last line of the method will be executed as a part of the same transaction.
    @Rollback(value = false) // If you have Spring Test, The transaction will automatically rollback.
    void saveProductsForCategory(){
        Category category = categoryRepository.findCategoryById(1L);
        System.out.println(category.getName());

        Product product = new Product();
        product.setTitle("Wheat");
        product.setPrice(89999);
        product.setDescription("Wheat");
        product.setCategory(category);
        product.setImageUrl("https://m.media-amazon.com/images/I/61n6Ovq6EdL._AC_SL1500_.jpg");
        productRepository.save(product);

        Product product1 = new Product();
        product1.setTitle("Bajra");
        product1.setPrice(89999);
        product1.setDescription("Bajra");
        product1.setCategory(category);
        product1.setImageUrl("https://m.media-amazon.com/images/I/61n6Ovq6EdL._AC_SL1500_.jpg");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setTitle("Jowar");
        product2.setPrice(89999);
        product2.setDescription("Jowar");
        product2.setCategory(category);
        product2.setImageUrl("https://m.media-amazon.com/images/I/61n6Ovq6EdL._AC_SL1500_.jpg");
        productRepository.save(product2);

    }

    // There will be 3 queries executed in the above method.
    // Select * from categories where id in (2,52)
    // Select * from products where category_id = 2
    // Select * from products where category_id = 52
    @Test
    @Transactional //Transaction is open and hence all the products will be fetched at the same time when fetching the category.
    void getProductsForCategory(){
        List<Category> categories = categoryRepository.findCategoryByIdIn(Arrays.asList(1L,2L));
        for (Category category:categories){
            System.out.println(category.getProducts().size());
            for (Product product:category.getProducts()){
                System.out.println(product.getTitle());
            }
        }
    }

}
