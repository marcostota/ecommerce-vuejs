package com.tota.EccommerceVuejs.DTO;

import com.tota.EccommerceVuejs.model.Product;

import java.util.List;

public class CartDTO {
    List<CartItemDto> cartItems;
    private double totalCost;


    public CartDTO() {
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
