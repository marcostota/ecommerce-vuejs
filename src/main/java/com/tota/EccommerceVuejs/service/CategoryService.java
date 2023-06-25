package com.tota.EccommerceVuejs.service;

import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category){
        categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return  categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory){
        Category category = new Category();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepo.save(category);
    }

    public boolean findByiD(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }
}
