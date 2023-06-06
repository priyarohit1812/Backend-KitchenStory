package com.kitchenstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitchenstory.model.Cart;


public interface ICartRepository extends JpaRepository<Cart, Long> {
}
