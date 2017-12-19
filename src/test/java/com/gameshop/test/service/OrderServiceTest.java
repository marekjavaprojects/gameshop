package com.gameshop.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gameshop.entity.Order;
import com.gameshop.entity.OrderDetails;
import com.gameshop.entity.Product;
import com.gameshop.entity.User;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;
import com.gameshop.repository.OrderDetailsRepository;
import com.gameshop.repository.OrderRepository;
import com.gameshop.repository.ProductRepository;
import com.gameshop.service.OrderService;
import com.gameshop.service.OrderServiceImpl;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderService;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private OrderDetailsRepository orderDetailsRepository;

	private Order order;
	private CartItem cartItem;
	private Product product;
	private ShoppingCart shoppingCart;
	private User user;
	private String productName;

	@Configuration
	static class OrderServiceTestContextConfiguration {

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		product = new Product("test", "testcategory", BigDecimal.valueOf(100.00), 20, "/test.png", "2017-12-11");
		product.setProductId(1L);
		cartItem = new CartItem(product);
		productName = cartItem.getProductName();
		user = new User("test", "testpass", "testpass", "test@test.pl");
		order = new Order(BigDecimal.valueOf(200.00), user);
		shoppingCart = new ShoppingCart(BigDecimal.valueOf(200.00), 1);
		shoppingCart.setCartItems(new ArrayList<CartItem>(Arrays.asList(cartItem)));
	}

	@Test
	public void transformCartItemIntoProductShouldReturnProductInstance() {
		orderService.transformCartItemIntoProduct(cartItem);
		when(productRepository.findProductByName(productName)).thenReturn(product);
		verify(productRepository, times(1)).findProductByName(productName);
		assertTrue(cartItem.getProductName().equals(productName));
		assertThat(orderService.transformCartItemIntoProduct(cartItem), instanceOf(Product.class));
	}

	@Test(expected = NullPointerException.class)
	public void transformCartItemIntoProductShouldThrowNPEWhenProductIsNotFound() {
		product = null;
		cartItem = new CartItem(product);
		String productName = "test";

		when(productRepository.findProductByName(productName)).thenReturn(null);
		verify(productRepository, times(1)).findProductByName(productName);
		orderService.transformCartItemIntoProduct(cartItem);
	}

	@Test
	public void checkIfProductsInCartAreAvailableShouldReturnEmptyListWhenProductIsAvailable() {
		when(productRepository.findProductByName(productName)).thenReturn(product);
		assertThat(orderService.checkIfProductsInCartAreAvailable(shoppingCart)).hasSize(0);
	}

	@Test
	public void checkIfProductsInCartAreAvailableShouldReturnNotEmptyListWhenProductIsUnavailable() {
		product.setQuantity(0);

		when(productRepository.findProductByName(productName)).thenReturn(product);		
		assertThat(orderService.checkIfProductsInCartAreAvailable(shoppingCart)).hasSize(1);
	}

	@Test
	public void processOrderIntoDatabaseShouldSuccessWhenProductsInCartAreAvailable() {
		when(productRepository.findProductByName(productName)).thenReturn(product);
		when(productRepository.getOne(product.getProductId())).thenReturn(product);
		orderService.processOrderIntoDatabase(shoppingCart, user);
		verify(productRepository, times(1)).saveAndFlush(product);
		assertThat(product.getQuantity()).isEqualTo(19);
	}

	@Test
	public void processOrderIntoDatabaseShouldFailWhenProductsInCartAreUnavailable() {
		product.setQuantity(0);
		
		when(productRepository.findProductByName(productName)).thenReturn(product);
		orderService.processOrderIntoDatabase(shoppingCart, user);		
		verify(orderRepository, times(0)).saveAndFlush(order);		
	}
	
}
