package dev.ayush.productserviceeve.service;

import dev.ayush.productserviceeve.dtos.ProductDTO;
import org.springframework.stereotype.Service;

public interface ProductService {
    String getAllProducts();
    String getSingleProduct(Long productId);
    String addNewProduct(ProductDTO productDTO);
    String updateProduct(Long productId);
    String deleteProduct(Long productId);
}
