package dev.ayush.productserviceeve.clients.fakestoreApi;

import dev.ayush.productserviceeve.dtos.RatingDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private RatingDTO rating;
}
