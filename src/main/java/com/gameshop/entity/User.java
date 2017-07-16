package com.gameshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	public User() {

	}

	public User(int userId, String firstName, String lastName, String login, String password, String email, String phone, int discount) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.discount = discount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "discount")
	private int discount;	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", discount=" + discount + "]";
	}

}
