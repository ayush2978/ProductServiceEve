package dev.ayush.productserviceeve.controller;

import dev.ayush.productserviceeve.dtos.ErrorResponseDTO;
import dev.ayush.productserviceeve.dtos.GetSingleObjectResponseDTO;
import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.exceptions.NotFoundException;
import dev.ayush.productserviceeve.models.Category;
import dev.ayush.productserviceeve.models.Product;
import dev.ayush.productserviceeve.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<GetSingleObjectResponseDTO> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        GetSingleObjectResponseDTO responseDTO=new GetSingleObjectResponseDTO();
        MultiValueMap<String, String> headers=new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess");
        Optional<Product> product= productService.getSingleProduct(productId);
        if (product.isEmpty()){
            throw new NotFoundException("Product with id: "+productId+" not found");
        }
        responseDTO.setProduct(product.get());
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

    @PatchMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductDTO productDTO){
        // convert productDTO to product
        Product product=new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImage());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        Product updatedProduct=productService.updateProduct(productId, product);
        ProductDTO updatedProductDTO=new ProductDTO();
        updatedProductDTO.setTitle(updatedProduct.getTitle());
        updatedProductDTO.setPrice(updatedProduct.getPrice());
        updatedProductDTO.setDescription(updatedProduct.getDescription());
        updatedProductDTO.setImage(updatedProduct.getImageUrl());
        updatedProductDTO.setCategory(updatedProduct.getCategory().getName());
        return updatedProductDTO;
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId,@RequestBody ProductDTO productDTO){
        // convert productDTO to product
        Product product=new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImage());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return productService.replaceProduct(productId, product);
    }


    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "Deleting Product: "+productId;
    }


    // For different type of exception, we define exception handler.
    // The below will only be called when exception is thrown from this controller only.
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(Exception e){
//        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
//        errorResponseDTO.setMessage(e.getMessage());
//        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
//    }
}
