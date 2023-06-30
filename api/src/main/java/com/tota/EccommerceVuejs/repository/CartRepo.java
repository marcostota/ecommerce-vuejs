package com.tota.EccommerceVuejs.repository;

import com.tota.EccommerceVuejs.model.Cart;
import com.tota.EccommerceVuejs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
