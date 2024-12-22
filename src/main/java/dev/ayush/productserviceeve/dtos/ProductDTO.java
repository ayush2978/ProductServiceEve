package dev.ayush.productserviceeve.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductDTO {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
