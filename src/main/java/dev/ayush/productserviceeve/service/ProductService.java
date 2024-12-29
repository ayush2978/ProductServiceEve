package dev.ayush.productserviceeve.service;

import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.models.Product;

import java.util.List;

// Services should never depend on DTOs. They should depend on domain objects. dtos end at controller.
public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long productId);
    Product addNewProduct(ProductDTO product);
    /*
    Product object has only those fields filled which are to be updated.
    Everything else is null.
     */
    Product updateProduct(Long productId, Product product);
    Boolean deleteProduct(Long productId);
}
