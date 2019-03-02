package com.capstone.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.model.Items;
import com.capstone.repo.ItemsRepository;
import com.capstone.service.ItemsService;

@Service
public class ItemsServiceImp implements ItemsService {

	@Autowired
	private ItemsRepository itemsRepository;

	@Override
	public List<Items> findAll() {
		return itemsRepository.findAll();
	}

	@Override
	public List<Items> findByitemCategory(String category) {

		return itemsRepository.findByitemCategory(category);
	}

	@Override
	public Optional<Items> findById(Integer id) {
		return itemsRepository.findById(id);
	}

	@Override
	public Items findOne(Integer id) {
		return itemsRepository.getOne(id);
	}

}
