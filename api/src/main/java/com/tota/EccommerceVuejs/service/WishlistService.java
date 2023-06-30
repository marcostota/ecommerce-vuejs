package com.tota.EccommerceVuejs.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.model.Wishlist;
import com.tota.EccommerceVuejs.repository.WishlistRepo;

@Service
@Transactional
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    ProductService productService;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }

    public List<ProductDTO> getWishListForUser(User user) {
        final List<Wishlist> wishLists = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDTO> productDtos = new ArrayList<>();
        for (Wishlist wishList : wishLists) {
            ProductDTO productDTO = productService.getProductDTO(wishList.getProduct());
            boolean productExists = false;

            for(ProductDTO existingProductDto :  productDtos){
                if(existingProductDto.getId().equals(productDTO.getId())){
                    productExists = true;
                    break;
                }
            }
            if(!productExists){
                productDtos.add(productDTO);
            }
        }
        return productDtos;
    }
}
