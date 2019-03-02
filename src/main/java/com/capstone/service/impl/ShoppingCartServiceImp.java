package com.capstone.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.model.Cart;
import com.capstone.repo.CartRepository;
import com.capstone.repo.ItemsRepository;
import com.capstone.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Override
	public Cart addItem(String userId, int itemsId, String itemsName, BigDecimal itemsPrice, String sizeOrdered,
			int qty) {

		List<Cart> cartItems = cartRepository.findByUserId(userId);
		for (Cart cartItemList : cartItems) {
			if ((cartItemList.getItemId() == itemsId) && (cartItemList.getSizeOrdered() == sizeOrdered)) {

				System.out.println("inside the add method");
				cartItemList.setQtyOrdered(cartItemList.getQtyOrdered() + qty);
				cartItemList.setItemTotal(itemsPrice.multiply(new BigDecimal(qty)));
			}
		}

		// if its a new item create a cartItem ,
		Cart cartItemList = new Cart();
		cartItemList.setUserId(userId);
		cartItemList.setItemId(itemsId);
		cartItemList.setItemName(itemsName);
		cartItemList.setSizeOrdered(sizeOrdered);
		cartItemList.setQtyOrdered(qty);
		cartItemList.setItemTotal(itemsPrice.multiply(new BigDecimal(qty)));
		cartItemList.setItemTotal(itemsPrice.multiply(new BigDecimal(qty)));

		cartRepository.save(cartItemList);
		return cartItemList;
	}

	@Override
	public Optional<Cart> findById(int cartid) {
		// TODO Auto-generated method stub
		return cartRepository.findById(cartid);
	}

	@Override
	public void removeItem(int cartID) {
		cartRepository.deleteByCartId(cartID);
	}

	@Override
	public List<Cart> findByUserId(String userId) {
		return cartRepository.findByUserId(userId);
	}

}
