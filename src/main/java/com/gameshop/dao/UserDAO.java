package com.gameshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.entity.User;

public interface UserDAO extends JpaRepository<User, Long>{

	public List<User> getAllUsers();

	public User getUser(int userId);

	public void createUser(User user);

	User findByEmail(String email);

	void deleteUser(User user);

	public User findByUserName(String userName);
}
