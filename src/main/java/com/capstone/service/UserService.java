package com.capstone.service;

import com.capstone.model.User;

public interface UserService {

	public void saveUser(User user);

	public boolean isUserAlreadyPresent(User user);

	User findByUsername(String username);

}
