package com.gameshop.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gameshop.entity.Order;
import com.gameshop.entity.OrderDetails;
import com.gameshop.entity.Product;
import com.gameshop.entity.Role;
import com.gameshop.entity.User;
import com.gameshop.repository.OrderDetailsRepository;
import com.gameshop.repository.OrderRepository;
import com.gameshop.repository.ProductRepository;
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
@DatabaseSetup({"/product-entries.xml","/role-entries.xml","/user-entries.xml","/orders-entries.xml","/order-details-entries.xml"})
public class OrderRepositoryTest {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void findOneShouldReturnOneOrder() {
		Long userId = 1L;
		String username = "test1";
		User userWithOrder = userRepository.findOne(userId);

		assertNotNull(userWithOrder);
		assertThat(userWithOrder.getUsername()).isEqualTo(username);
	}
	
	@Test
	public void saveShouldCreateEntryInDatabase() {
		User user = userRepository.findOne(2L);
		Order order = new Order(BigDecimal.valueOf(119.99), user);
		orderRepository.save(order);
		Order newOrder = orderRepository.findOne(4L);
		List<Order> orders = orderRepository.findAll();

		assertNotNull(newOrder);
		assertThat(orders).hasSize(4);
		assertThat(newOrder.getUser().getId()).isEqualTo(user.getId());
	}
	
	@Test
	public void deleteEntryInDatabaseShouldReturnTwoOrders() {
		User user = userRepository.findOne(2L);
		Order order = orderRepository.findOne(1l);
		orderRepository.delete(order);
		List<Order> orders = orderRepository.findAll();
		
		assertNotNull(user);
		assertNotNull(order);
		assertThat(orders).hasSize(2);
	}
}
