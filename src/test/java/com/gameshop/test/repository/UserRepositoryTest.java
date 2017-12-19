package com.gameshop.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gameshop.entity.Role;
import com.gameshop.entity.User;
import com.gameshop.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/gameshop-configuration-tests.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@WebAppConfiguration
@EnableSpringDataWebSupport
@Transactional
@DatabaseSetup("/user-entries.xml")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	

	@Test
	public void findAllShouldReturnFourUsers() {
		List<User> searchResults = userRepository.findAll();

		assertThat(searchResults).hasSize(4);
	}

	@Test
	public void findByUsernameShouldReturnOneSpecificUser() {
		String username = "test3";
		User foundUser = userRepository.findByUsername(username);

		assertThat(foundUser.getUsername()).isEqualTo(username);
	}

	@Test
	public void findOneShouldReturnUserById() {
		Long userId = 1L;
		User foundUser = userRepository.findOne(userId);

		assertThat(foundUser.getId()).isEqualTo(userId);
	}
	
	@Test
	@Rollback
	public void saveUserShouldCreateEntryInDatabase() {
		
		User user = new User("test5", "$7653dd1", "$7653dd1", "test5@test.pl");
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setName("user");
		roles.add(role);
		user.setRoles(roles);
		userRepository.saveAndFlush(user);
		User foundUser = userRepository.findOne(user.getId());
		
		assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	@Rollback
	public void deleteUserShouldDeleteEntryFromDatabase() {		
		Long userId = 1L;		
		userRepository.delete(userId);		
		
		assertNull(userRepository.findOne(userId));
	}
}
