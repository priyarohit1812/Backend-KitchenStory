package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.Cart;
import com.kitchenstory.repository.ICartRepository;

@Service(value = "cartService")
public class CartService implements ICartService {
	@Autowired
	private ICartRepository cartRepository;
	
	@Override
	public List<Cart> fetchCartList() {
		return cartRepository.findAll();
	}

	@Override
	public Cart saveCart(Cart cart) {
		return this.cartRepository.save(cart);
	}

	@Override
	public boolean deleteCart(Long cartId) {
		this.cartRepository.deleteById(cartId);
		return !this.cartRepository.existsById(cartId);
	}

	@Override
	public Cart getCart(Long cartId) {
		Optional<Cart> optCart = this.cartRepository.findById(cartId);
		if (optCart !=null && optCart.isPresent()) {
			return optCart.get();
		}else {
			return null;
		}
	}

}
