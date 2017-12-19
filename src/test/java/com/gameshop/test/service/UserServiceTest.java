package com.gameshop.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gameshop.entity.Role;
import com.gameshop.entity.User;
import com.gameshop.repository.RoleRepository;
import com.gameshop.repository.UserRepository;
import com.gameshop.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private RoleRepository roleRepository;
	@Mock
	private BCryptPasswordEncoder bcryptEncoder;

	private List<User> usersList;
	private List<Role> userRoles;

	@Configuration
	static class UserServiceTestContextConfiguration {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		usersList = new ArrayList<>(Arrays.asList(new User("user1", "user", "user", "user@user.pl"),
				new User("user2", "user2", "user2", "user2@user.pl"),
				new User("user3", "user3", "user3", "user3@user.pl")));
		Role userRole = new Role("user");
		userRoles = new ArrayList<>(Arrays.asList(userRole));

	}

	@Test
	public void getAllUsersShouldReturnThreeUsers() {
		when(userRepository.findAll()).thenReturn(usersList);
		List<User> users = userService.getAllUsers();
		assertThat(users).hasSize(3);
	}

	@Test
	public void getUserShouldReturnOneUser() {
		Long userId = 1L;
		User user = usersList.get(0);
		user.setId(userId);

		when(userRepository.findOne(userId)).thenReturn(user);
		User foundUser = userService.getUserById(userId);
		assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
	}

	@Test
	public void createUserShouldPutUserInDatabase() {
		String encryptedPassword = "encryptedPassword";
		User user = usersList.get(0);

		when(bcryptEncoder.encode(user.getPassword())).thenReturn(encryptedPassword);
		when(roleRepository.findAll()).thenReturn(userRoles);
		userService.createUser(user);
		verify(roleRepository, times(1)).findAll();
		assertThat(user.getPassword()).isEqualTo(encryptedPassword);
	}

	@Test
	public void deleteUserShouldInvokeDeleteOnUserRepository() {
		User user = usersList.get(0);

		userService.deleteUser(user);
		verify(userRepository, times(1)).delete(user);
	}

	@Test
	public void getUserByUsernameShouldReturnOneUser() {
		String userName = "test2";
		User user = usersList.get(1);

		when(userRepository.findByUsername(userName)).thenReturn(user);
		User foundUser = userService.getUserByUsername(userName);
		assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
	}
}
