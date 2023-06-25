package com.tota.EccommerceVuejs.repository;

import com.tota.EccommerceVuejs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
