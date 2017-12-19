package com.gameshop.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gameshop.entity.Product;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;
import com.gameshop.service.ShoppingCartService;
import com.gameshop.service.ShoppingCartServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartServiceTest {

	@InjectMocks
	private ShoppingCartServiceImpl shoppingCartService;
	@Mock
	private ShoppingCart shoppingCart;

	private BigDecimal totalPrice;
	private CartItem cartItem;
	private Product firstProduct;
	private Product secondProduct;
	private List<CartItem> cartItemsList;

	@Configuration
	static class ShoppingCartServiceTestContextConfiguration {

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		totalPrice = BigDecimal.ZERO;
		firstProduct = new Product("test1", "testcategory1", BigDecimal.valueOf(119.99), 20, "/test1.png",
				"2017-10-11");
		firstProduct.setProductId(1L);
		secondProduct = new Product("test2", "testcategory2", BigDecimal.valueOf(119.99), 20, "/test2.png",
				"2017-10-22");
		secondProduct.setProductId(2L);
		cartItem = new CartItem(firstProduct);
		cartItemsList = new ArrayList<>();
		cartItemsList.add(cartItem);

	}

	@Test
	public void addToCartShouldSuccessWhenItemIsNotInCart() {
		CartItem newCartItem = new CartItem(secondProduct);

		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		shoppingCartService.addToCart(newCartItem);
		verify(shoppingCart, times(2)).getCartItems();
		verify(shoppingCart, times(1)).addProduct(newCartItem);
		verify(shoppingCart, times(1)).setTotalPrice(newCartItem.getSubtotalPrice());
		assertThat(shoppingCartService.getTotalPrice()).isEqualTo(BigDecimal.valueOf(119.99));
	}

	@Test
	public void addToCartShouldFailWhenItemIsAlreadyInCart() {
		CartItem newCartItem = new CartItem(firstProduct);

		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		shoppingCartService.addToCart(newCartItem);
		verify(shoppingCart, never()).addProduct(newCartItem);
		assertThat(shoppingCartService.getTotalPrice()).isEqualTo(BigDecimal.valueOf(0));
	}

	@Test
	public void updateShoppingCartShouldUpdateQuantityWhenItemIsAlreadyInCart() {
		String[] quantity = new String[] { "10" };
		cartItem.setQuantity(20);
		int cartItemQuantityBeforeUpdate = cartItemsList.get(0).getQuantity();
		
		assertThat(cartItemQuantityBeforeUpdate).isEqualTo(20);
		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		shoppingCartService.updateCartItem(quantity);
		int cartItemQuantityAfterUpdate = cartItemsList.get(0).getQuantity();
		assertThat(cartItemQuantityAfterUpdate).isEqualTo(10);
	}

	@Test
	public void findCartItemByNameShouldReturnSpecificCartItem() {
		String productName = "test1";

		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		CartItem foundCartItem = shoppingCartService.findCartItemByName(productName);
		assertThat(foundCartItem.getProductName()).isEqualTo(cartItemsList.get(0).getProductName());
	}

	@Test
	public void findCartItemByNameShouldReturnNullWhenCartItemNotFound() {
		String productName = "test3";

		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		CartItem cartItem = shoppingCartService.findCartItemByName(productName);
		assertThat(cartItem).isNull();
	}

	@Test
	public void findCartItemByIdShouldReturnSpecificCartItem() {
		Long productId = 1L;
		firstProduct.setProductId(productId);
		
		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		CartItem foundCartItem = shoppingCartService.findCartItemById(productId);
		assertThat(foundCartItem.getId()).isEqualTo(cartItemsList.get(0).getId());
	}
	
	@Test
	public void findCartItemByIdShouldReturnNullWhenCartItemNotFound() {
		Long productId = 3L;
		firstProduct.setProductId(productId);
		
		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		CartItem foundCartItem = shoppingCartService.findCartItemById(productId);
		assertThat(foundCartItem).isNull();
	}
	
	@Test
	public void deleteItemFromCartShouldReturnNullWhenCartItemNotFound() {		
		when(shoppingCart.getCartItems()).thenReturn(cartItemsList);
		shoppingCartService.deleteItemFromCart(cartItem);
	}	
}
