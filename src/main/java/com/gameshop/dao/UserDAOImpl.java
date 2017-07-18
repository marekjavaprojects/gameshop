package com.gameshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.gameshop.entity.User;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
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
		try {		
			currentSession.beginTransaction();
			currentSession.save(user);
			currentSession.getTransaction().commit();
			
		} finally {			
			sessionFactory.close();
		}		
	}

	@Override
	public void deleteUser(int userId) {
	}

}
