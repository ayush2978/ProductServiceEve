package dev.ayush.productserviceeve.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}) // Please save the category along with the product.
    private Category category;
    private String imageUrl;
}
