package com.tota.EccommerceVuejs.service;

import com.tota.EccommerceVuejs.DTO.AddCartDTO;
import com.tota.EccommerceVuejs.DTO.CartDTO;
import com.tota.EccommerceVuejs.DTO.CartItemDto;
import com.tota.EccommerceVuejs.exceptions.CustomException;
import com.tota.EccommerceVuejs.model.Cart;
import com.tota.EccommerceVuejs.model.Product;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    private CartRepo cartRepo;

    public void addToCart(AddCartDTO addCartDTO, User user) {

        Product product = productService.findById(addCartDTO.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addCartDTO.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepo.save(cart);
    }

    public CartDTO listCartItems(User user) {
        List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItemDtos = new ArrayList<>();
        double totalCost = 0;
        for(Cart cart: cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItemDtos.add(cartItemDto);
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalCost(totalCost);
        cartDTO.setCartItems(cartItemDtos);
        return cartDTO;
    }


    public void deleteCartItem(Integer cartItemId, User user){
        Optional<Cart> opCartItem = cartRepo.findById(cartItemId);

        if(opCartItem.isEmpty()){
            throw new CustomException("Cart item id is invalid " + cartItemId );
        }

        Cart cart = opCartItem.get();

        if(cart.getUser() != user){
            throw new CustomException("cart item does not belong to user " + cartItemId);
        }

        cartRepo.delete(cart);
    }
}
