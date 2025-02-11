package com.mystore.codeshop.repository;

import com.mystore.codeshop.entity.Cart;
import com.mystore.codeshop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
