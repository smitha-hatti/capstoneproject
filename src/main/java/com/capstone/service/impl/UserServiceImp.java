package com.capstone.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.capstone.model.User;
import com.capstone.repo.ItemsRepository;
import com.capstone.repo.UserRepository;
import com.capstone.service.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemsRepository itemsRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		user.setRole("SITE_USER");
		userRepository.save(user);
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {

		boolean flag = false;
		List<User> tempUser = userRepository.findAll();
		for (User temp : tempUser) {
			if ((user.getEmail()).equals(temp.getEmail())) {
				flag = true;
				break;
			} else
				flag = false;

		}
		return flag;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String username) {
		return userRepository.findByEmail(username);
	}

}
