package dev.ayush.productserviceeve.service;

import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.models.Category;
import dev.ayush.productserviceeve.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        // We used ProductDTO[] instead of List<ProductDTO> because of Type Erosion in java.
        // Run time works on the data type of the actual object present in the variable
        // It does not work on the data type of the variable
        // List<ProductDTO> productDTOList=restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        //this will return List<LinkedHashMap> insetad of ProductDTO and if we do obj.getObject(), at run time, the variable will have type of that object only i.e LinkedHashMap.
        ResponseEntity<ProductDTO[]> obj=restTemplate.getForEntity("https://fakestoreapi.com/products", ProductDTO[].class);
        ProductDTO []productDTOList=obj.getBody();
        List<Product> productList=new ArrayList<>();
        for (ProductDTO productDTO:productDTOList){
            Product product=new Product();
            product.setTitle(productDTO.getTitle());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setImageUrl(productDTO.getImage());
            Category category=new Category();
            category.setName(productDTO.getCategory());
            product.setCategory(category);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductDTO> response =restTemplate.getForEntity("https://fakestoreapi.com/products/{id}"+productId, ProductDTO.class, productId);
        ProductDTO productDTO=response.getBody();
        // convert into product
        Product product=new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImage());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product addNewProduct(ProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO> productResponse = restTemplate.postForEntity("https://fakestoreapi.com/products", product, ProductDTO.class);
        ProductDTO productDTO = productResponse.getBody();
        Product newProduct = new Product();
        newProduct.setId(productDTO.getId());
        newProduct.setTitle(productDTO.getTitle());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setImageUrl(productDTO.getImage());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        newProduct.setCategory(category);
        return newProduct;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        return null;
    }
}
