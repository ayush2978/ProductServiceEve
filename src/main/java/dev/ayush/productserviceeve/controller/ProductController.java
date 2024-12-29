package dev.ayush.productserviceeve.controller;

import dev.ayush.productserviceeve.dtos.GetSingleObjectResponseDTO;
import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.models.Product;
import dev.ayush.productserviceeve.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Product> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        ResponseEntity<Product> responseEntity=new ResponseEntity(products, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetSingleObjectResponseDTO> getSingleProduct(@PathVariable("productId") Long productId){
        GetSingleObjectResponseDTO responseDTO=new GetSingleObjectResponseDTO();
        MultiValueMap<String, String> headers=new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess");
        responseDTO.setProduct(productService.getSingleProduct(productId));
        ResponseEntity<GetSingleObjectResponseDTO> responseEntity=new ResponseEntity<>(responseDTO,headers, HttpStatus.OK);
        return responseEntity;
    }

    // For every controller, have a separate specific DTO Object. What information do you need in order to do that specific operation.
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO){
        Product newProduct=productService.addNewProduct(productDTO);
        ResponseEntity<Product> responseEntity=new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        return responseEntity;
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
