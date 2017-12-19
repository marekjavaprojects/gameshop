package com.gameshop.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gameshop.entity.Product;
import com.gameshop.entity.Role;
import com.gameshop.entity.User;
import com.gameshop.repository.ProductRepository;
import com.gameshop.repository.UserRepository;
import com.gameshop.service.CustomUserDetailService;
import com.gameshop.service.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomUserDetailServiceTest {

	@InjectMocks
	private CustomUserDetailService customUserDetailService;
	@Mock
	private UserRepository userRepository;

	private User user;
	private Role role;
	private Set<Role> roles;

	@Configuration
	static class CustomUserDetailServiceTestContextConfiguration {

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("test", "pass", "pass", "test@test.pl");
		role = new Role("user");
		roles = new HashSet<>(Arrays.asList(role));
		user.setRoles(roles);

	}

	@Test
	public void loadUserByUsernameShouldReturnUserDetails() {
		String userName = "test";

		when(userRepository.findByUsername(userName)).thenReturn(user);
		UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
		assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
		assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameShouldThrowExceptionWhenUserNotFound() {		
		String userName = "notFound";

		when(userRepository.findByUsername(userName)).thenThrow(UsernameNotFoundException.class);
		UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
		assertThat(userDetails).isNull();
	}
}
