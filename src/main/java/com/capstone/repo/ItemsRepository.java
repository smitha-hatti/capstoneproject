package com.capstone.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.model.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {

	List<Items> findByitemCategory(String category);
	
	List<Items> findAll();
	
    Optional<Items> findById(Integer id);

    void save(Set<Items> keySet);

}