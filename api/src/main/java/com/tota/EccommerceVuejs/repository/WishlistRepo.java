package com.tota.EccommerceVuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.model.Wishlist;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);

}
