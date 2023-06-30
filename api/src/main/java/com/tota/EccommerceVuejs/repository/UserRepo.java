package com.tota.EccommerceVuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tota.EccommerceVuejs.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
