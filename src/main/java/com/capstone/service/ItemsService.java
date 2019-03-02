package com.capstone.service;

import java.util.List;
import java.util.Optional;

import com.capstone.model.Items;

public interface ItemsService {
	
	List<Items> findAll();

	List<Items> findByitemCategory(String category);

	Optional<Items> findById(Integer id);

	Items findOne(Integer id);

}
