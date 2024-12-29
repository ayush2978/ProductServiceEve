package dev.ayush.productserviceeve.dtos;

import dev.ayush.productserviceeve.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleObjectResponseDTO {
    private Product product;
}
