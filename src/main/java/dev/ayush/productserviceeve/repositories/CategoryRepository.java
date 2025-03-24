package dev.ayush.productserviceeve.repositories;

import dev.ayush.productserviceeve.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findCategoryById(Long id);

    List<Category> findCategoryByIdIn(List<Long> ids);
}
