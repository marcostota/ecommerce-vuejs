package com.tota.EccommerceVuejs.service;

import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.model.Product;
import com.tota.EccommerceVuejs.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public void createProduct(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageUrl());
        product.setCategory(category);
        productRepo.save(product);
    }


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product : products){
            productDTOS.add(getProductDTO(product));
        }

        return productDTOS;
    }

    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageURL());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }

    public void updateProduct(ProductDTO productDTO, Integer productId) throws Exception {
        Optional<Product> opProduct = productRepo.findById(productId);
        if(!opProduct.isPresent()){
            throw new Exception("Product not found");
        }
        Product product = opProduct.get();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageUrl());
        productRepo.save(product);
    }
}
