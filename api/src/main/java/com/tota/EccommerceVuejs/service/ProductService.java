package com.tota.EccommerceVuejs.service;

import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.exceptions.ProductNotExistException;
import com.tota.EccommerceVuejs.model.Category;
import com.tota.EccommerceVuejs.model.Product;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.repository.CartRepo;
import com.tota.EccommerceVuejs.repository.CategoryRepo;
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

    @Autowired
    private CategoryRepo categoryRepo;

    public void createProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageURL());
        product.setCategory(category);
        productRepo.save(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(getProductDTO(product));
        }

        return productDTOS;
    }

    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }

    public void updateProduct(ProductDTO productDTO, Integer productId) throws Exception {
        Optional<Product> opProduct = productRepo.findById(productId);
        if (!opProduct.isPresent()) {
            throw new Exception("Product not found");
        }
        Product product = opProduct.get();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageURL());

        Optional<Category> opCategoy = categoryRepo.findById(productDTO.getCategoryId());
        if (!opCategoy.isPresent()) {
            throw new Exception("Category not found");
        }
        Category category = opCategoy.get();
        System.out.println(category.getId());
        product.setCategory(opCategoy.get());
        productRepo.save(product);
    }

    public Product findById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }


}
