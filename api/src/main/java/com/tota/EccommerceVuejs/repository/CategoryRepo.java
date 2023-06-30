package com.tota.EccommerceVuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tota.EccommerceVuejs.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
