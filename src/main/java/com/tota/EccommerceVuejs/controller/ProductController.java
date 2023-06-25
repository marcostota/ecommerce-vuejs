package com.tota.EccommerceVuejs.controller;

import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.common.ApiResponse;
import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.model.Product;
import com.tota.EccommerceVuejs.repository.CategoryRepo;
import com.tota.EccommerceVuejs.service.CategoryService;
import com.tota.EccommerceVuejs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepo categoryRepo;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        Optional<Category> opCategory = categoryRepo.findById(productDTO.getCategoryId());
        if(!opCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category not been founded"), HttpStatus.NOT_FOUND );
        }
        productService.createProduct(productDTO, opCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDTO productDTO) throws Exception {
        Optional<Category> opCategory = categoryRepo.findById(productDTO.getCategoryId());
        if(!opCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category not been founded"), HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productDTO, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);

    }

}
