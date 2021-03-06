package com.gameshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameshop.entity.Order;
import com.gameshop.entity.OrderDetails;
import com.gameshop.entity.Product;
import com.gameshop.entity.User;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;
import com.gameshop.repository.OrderDetailsRepository;
import com.gameshop.repository.OrderRepository;
import com.gameshop.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	private Product product;
	private OrderDetails orderDetails;
	
	@Override
	@Transactional
	public void processOrderIntoDatabase(ShoppingCart shoppingCart, User user) {
		if (checkIfProductsInCartAreAvailable(shoppingCart).isEmpty()) {
			Order order = new Order(shoppingCart.getTotalPrice(), user);
			orderRepository.saveAndFlush(order);
			for (CartItem cartItem : shoppingCart.getCartItems()) {
				product = transformCartItemIntoProduct(cartItem);
				orderDetails = new OrderDetails(product, order, cartItem.getQuantity());
				orderDetailsRepository.save(orderDetails);
				updateProductsQuantityInDatabase(product, cartItem);
			}
		}
	}

	@Override
	@Transactional
	public Product transformCartItemIntoProduct(CartItem cartItem) {
		Product product = productRepository.findProductByName(cartItem.getProductName());
		return product;
	}

	@Override
	@Transactional
	public List<Product> checkIfProductsInCartAreAvailable(ShoppingCart cart) {
		List<Product> notAvailableProductsIds = new ArrayList<Product>();
		for (CartItem item : cart.getCartItems()) {
			Product product = productRepository.findProductByName(item.getProductName());
			if (item.getQuantity() > product.getQuantity()) {
				notAvailableProductsIds.add(product);
			}
		}

		return notAvailableProductsIds;
	}
	
	@Transactional
	private void updateProductsQuantityInDatabase(Product product, CartItem item) {
		product.setQuantity(productRepository.getOne(product.getProductId()).getQuantity() - item.getQuantity());
		productRepository.saveAndFlush(product);
	}

}