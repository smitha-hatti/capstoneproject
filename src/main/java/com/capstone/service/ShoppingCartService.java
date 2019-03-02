package com.capstone.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.capstone.model.Cart;

public interface ShoppingCartService {
  
	Cart addItem(String userId, int itemsId, String itemsName, BigDecimal itemsPrice, String sizeOrdered, int qty);

    
	Optional<Cart> findById(int cartid);
	
	List<Cart> findByUserId(String userId);
	void removeItem(int cartID);
	    
}
