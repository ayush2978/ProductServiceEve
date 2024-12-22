package dev.ayush.productserviceeve.controller;

import dev.ayush.productserviceeve.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String getAllCategories(){
        return "Getting All Categories";
    }

    @GetMapping("/categories/{id}")
    public String getProductsInCategory(){
        return "getting Products in Category";
    }
}
