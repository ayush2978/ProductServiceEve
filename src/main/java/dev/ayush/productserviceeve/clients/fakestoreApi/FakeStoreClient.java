package dev.ayush.productserviceeve.clients.fakestoreApi;


import dev.ayush.productserviceeve.dtos.ProductDTO;
import dev.ayush.productserviceeve.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }

    private  <T> ResponseEntity<T> requestForEntity(HttpMethod methodType, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate= restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, methodType, requestCallback, responseExtractor, uriVariables);
    }
    public List<FakeStoreProductDTO> getAllProducts(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        // We used ProductDTO[] instead of List<ProductDTO> because of Type Erosion in java.
        // Run time works on the data type of the actual object present in the variable
        // It does not work on the data type of the variable
        // List<ProductDTO> productDTOList=restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        //this will return List<LinkedHashMap> insetad of ProductDTO and if we do obj.getObject(), at run time, the variable will have type of that object only i.e LinkedHashMap.
        ResponseEntity<FakeStoreProductDTO[]> obj=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        FakeStoreProductDTO []productDTOList=obj.getBody();
        return Arrays.asList(productDTOList);
    }
    public Optional<FakeStoreProductDTO> getSingleProduct(Long productId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        // FakeStoreProductDTO should be exactly one-one mapping to what FakeStoreProduct is returning
        // whereas ProductDTO should be exactly one-one mapping on what we are returning.
        // Today they are same, but tommorow they might not same
        ResponseEntity<FakeStoreProductDTO> response =restTemplate.getForEntity("https://fakestoreapi.com/products/{id}"+productId, FakeStoreProductDTO.class, productId);
        return Optional.ofNullable(response.getBody());
    }
    public FakeStoreProductDTO addNewProduct(ProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> productResponse = restTemplate.postForEntity("https://fakestoreapi.com/products", product, FakeStoreProductDTO.class);
        FakeStoreProductDTO productDTO = productResponse.getBody();
        return productDTO;
    }
    /*
    Product object has only those fields filled which are to be updated.
    Everything else is null.
     */
    public FakeStoreProductDTO updateProduct(Long productId, Product product){
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDTO> updatedProductEntity=requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}", fakeStoreProductDTO, FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO updatedProduct= updatedProductEntity.getBody();
        return updatedProduct;
    }
    Boolean deleteProduct(Long productId){
        return null;
    }

    public FakeStoreProductDTO replaceProduct(Long productId, Product product){
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDTO> updatedProductEntity=requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", fakeStoreProductDTO, FakeStoreProductDTO.class, productId);
        FakeStoreProductDTO updatedProduct= updatedProductEntity.getBody();
        return updatedProduct;
    }
}
