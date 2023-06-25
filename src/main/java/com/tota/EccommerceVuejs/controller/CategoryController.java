package com.tota.EccommerceVuejs.controller;

import com.tota.EccommerceVuejs.common.ApiResponse;
import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @PostMapping("/edit/{categoryId}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findByiD(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false, "category not found"), HttpStatus.BAD_REQUEST);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }
}
