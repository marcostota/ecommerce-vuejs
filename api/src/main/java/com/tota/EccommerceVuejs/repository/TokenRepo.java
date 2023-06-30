package com.tota.EccommerceVuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tota.EccommerceVuejs.model.AuthenticationToken;
import com.tota.EccommerceVuejs.model.User;

public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}