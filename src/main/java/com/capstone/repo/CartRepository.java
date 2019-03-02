package com.capstone.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.model.Cart;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
	
	List<Cart> findByUserId(String userId) ;
	
		Cart findByCartId(int cartId);
		
		int deleteByCartId(int cartId);
		
		void removeByUserId(String userEmail);
}
