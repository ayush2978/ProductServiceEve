package dev.ayush.productserviceeve.service;

import dev.ayush.productserviceeve.clients.fakestoreApi.FakeStoreProductDTO;
import dev.ayush.productserviceeve.clients.fakestoreApi.FakeStoreClient;
import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.models.Category;
import dev.ayush.productserviceeve.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {

    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient=fakeStoreClient;
    }

    public Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO productDTO){
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
    public List<Product> getAllProducts() {
        List<FakeStoreProductDTO> fakeStoreProduct =fakeStoreClient.getAllProducts();
        List<Product> productList=new ArrayList<>();
        for (FakeStoreProductDTO productDTO:fakeStoreProduct){
            Product product=convertFakeStoreProductDTOToProduct(productDTO);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        Optional<FakeStoreProductDTO> productDTO=fakeStoreClient.getSingleProduct(productId);
        if (productDTO==null){
            return Optional.empty();
        }
        // convert into product
        return Optional.of(convertFakeStoreProductDTOToProduct(productDTO.get()));
    }

    @Override
    public Product addNewProduct(ProductDTO product) {
        FakeStoreProductDTO productDTO=fakeStoreClient.addNewProduct(product);
        return convertFakeStoreProductDTOToProduct(productDTO);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDTO updatedProduct=fakeStoreClient.updateProduct(productId, product);
        return convertFakeStoreProductDTOToProduct(updatedProduct);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDTO updatedProduct=fakeStoreClient.replaceProduct(productId, product);
        return convertFakeStoreProductDTOToProduct(updatedProduct);
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        return null;
    }
}
