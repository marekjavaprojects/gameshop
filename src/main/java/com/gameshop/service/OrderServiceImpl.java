package com.gameshop.service;

import java.util.Map;

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
	OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;	
	Map<String, Integer> orders;

	@Override
	public void processOrderIntoDatabase(ShoppingCart shoppingCart, User user) {
		
		Order order = new Order(shoppingCart.getTotalPrice(), user);
		Product product;
		System.out.println("order id: " + order.getId());
		orderRepository.saveAndFlush(order);
		OrderDetails orderDetails;
		for(CartItem cartItem : shoppingCart.getCartItems()) {
			orderDetails = new OrderDetails();
			product = transformCartItemIntoProduct(cartItem);
			orderDetails.setOrder(order);
			orderDetails.setProduct(product);
			orderDetails.setQuantity(cartItem.getQuantity());
			System.out.println("order det id: " + orderDetails.getOrderDetailsId());
			orderDetailsRepository.save(orderDetails);
		}
	}

	@Override
	public Product transformCartItemIntoProduct(CartItem cartItem) {
		Product product = productRepository.findProductByName(cartItem.getProductName());
		return product;
	}
	
	private boolean checkIfProductIsAvailable(Long id) {		
		if(productRepository.findOne(id).getQuantity() > 0) {
			return true;
		}
		
		return false;		
	}
	
	private void updateProductQuantity(Long id) {
		if(checkIfProductIsAvailable(id)) {
			int quantity = productRepository.findOne(id).getQuantity();
		} else {
			
		}
	}
}
