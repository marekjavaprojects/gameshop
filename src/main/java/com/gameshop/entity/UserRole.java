package com.gameshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private int userRoleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "user_id")
	private String userId;

}
