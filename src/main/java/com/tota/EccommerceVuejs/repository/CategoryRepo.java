package com.tota.EccommerceVuejs.repository;

import com.tota.EccommerceVuejs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
