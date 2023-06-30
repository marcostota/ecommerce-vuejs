package com.tota.EccommerceVuejs.service;

import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory) throws Exception {
        Optional<Category> opCategory = categoryRepo.findById(categoryId);
        if (!opCategory.isPresent()) {
            throw new Exception("Category not founded!");
        }
        Category category = opCategory.get();
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageURL(updateCategory.getImageURL());
        categoryRepo.save(category);
    }

    public boolean findByiD(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }
}
