package com.gameshop.dao;

import java.util.List;

import com.gameshop.entity.User;

public interface UserDAO {

	public List<User> getAllUsers();

	public User getUser(int userId);

	public void createUser(User user);

	public void deleteUser(int userId);

	public User findByUserName(String userName);
}
