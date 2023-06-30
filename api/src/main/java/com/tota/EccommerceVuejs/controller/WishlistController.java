package com.tota.EccommerceVuejs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.common.ApiResponse;
import com.tota.EccommerceVuejs.model.Product;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.model.Wishlist;
import com.tota.EccommerceVuejs.service.AuthenticationtokenService;
import com.tota.EccommerceVuejs.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private AuthenticationtokenService authenticationtokenService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
        authenticationtokenService.authenticate(token);
        User user = authenticationtokenService.getUser(token);
        Wishlist wishList = new Wishlist(user, product);
        wishlistService.createWishlist(wishList);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token) {

        // authenticate the token
        authenticationtokenService.authenticate(token);

        // find the user

        User user = authenticationtokenService.getUser(token);

        List<ProductDTO> productDtos = wishlistService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }

}
