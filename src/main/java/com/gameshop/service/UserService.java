package com.gameshop.service;

import java.util.List;

import com.gameshop.entity.User;

public interface UserService {
	public List<User> getAllUsers();
	
	public User getUserById(Long id);
	
	public void createUser(User user);

	public void deleteUser(User user);

	public User getUserByUsername(String username);

}
