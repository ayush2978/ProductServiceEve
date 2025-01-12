package dev.ayush.productserviceeve.repositories;

import dev.ayush.productserviceeve.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
}
