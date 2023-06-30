package com.tota.EccommerceVuejs.controller;

import com.tota.EccommerceVuejs.DTO.AddCartDTO;
import com.tota.EccommerceVuejs.DTO.CartDTO;
import com.tota.EccommerceVuejs.DTO.ProductDTO;
import com.tota.EccommerceVuejs.common.ApiResponse;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.service.AuthenticationtokenService;
import com.tota.EccommerceVuejs.service.CartService;
import com.tota.EccommerceVuejs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationtokenService authenticationtokenService;

    @PostMapping("/add")
     public ResponseEntity<ApiResponse> addToCart (@RequestBody AddCartDTO addCartDTO, @RequestParam("token") String token){
            authenticationtokenService.authenticate(token);
            User user = authenticationtokenService.getUser(token);

            cartService.addToCart(addCartDTO, user);
            return new ResponseEntity( new ApiResponse(true, "Products added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
            public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token){
            authenticationtokenService.authenticate(token);
            User user = authenticationtokenService.getUser(token);

            CartDTO cartDTO = cartService.listCartItems(user);
            return new ResponseEntity<>(cartDTO ,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartItemId}")
    public  ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int cartItemId, @RequestParam("token") String token){
        authenticationtokenService.authenticate(token);
        User user = authenticationtokenService.getUser(token);

        cartService.deleteCartItem(cartItemId, user);

        return new ResponseEntity<>(new ApiResponse(true,"CartItem Deleted"), HttpStatus.OK);
    }
}

