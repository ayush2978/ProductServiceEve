package dev.ayush.productserviceeve.controller;

import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(){
        productService.getAllProducts();
        return "All Products";
    }

    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "product returned for productId: " + productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDTO productDTO){
        return "Adding New Product: "+productDTO;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId){
        return "Updating Product for productId: " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Product: "+productId;
    }
}
