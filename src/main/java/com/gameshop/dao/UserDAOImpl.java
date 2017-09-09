package com.gameshop.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gameshop.entity.Role;
import com.gameshop.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Session currentSession;

	@Override
	public List<User> getAllUsers() {
		currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User", User.class);
		List<User> users = query.getResultList();

		return users;
	}

	@Override
	public User getUser(int userId) {
		currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, userId);

		return user;
	}

	@Override
	public void createUser(User user) {
		currentSession = sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setName("ROLE_USER");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(role)));
		currentSession.saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		System.out.println("findByUserName");
		currentSession = sessionFactory.getCurrentSession();

		List<User> users = currentSession.createQuery("from User where username=?").setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void deleteUser(int userId) {
	}

}
