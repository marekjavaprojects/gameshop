package com.gameshop.service;

import java.util.ArrayList;
import java.util.List;
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
	List<Product> notAvailableProducts;

	@Override
	public void processOrderIntoDatabase(ShoppingCart shoppingCart, User user) {
		notAvailableProducts = checkIfProductsInCartAreAvailable(shoppingCart);
		if (notAvailableProducts.isEmpty()) {
			Order order = new Order(shoppingCart.getTotalPrice(), user);
			Product product;
			orderRepository.saveAndFlush(order);
			OrderDetails orderDetails;
			for (CartItem cartItem : shoppingCart.getCartItems()) {
				orderDetails = new OrderDetails();
				product = transformCartItemIntoProduct(cartItem);
				orderDetails.setOrder(order);
				orderDetails.setProduct(product);
				orderDetails.setQuantity(cartItem.getQuantity());
				orderDetailsRepository.save(orderDetails);
				updateProductsQuantityInDataBase(product, cartItem);
			}
		}
	}

	@Override
	public Product transformCartItemIntoProduct(CartItem cartItem) {
		Product product = productRepository.findProductByName(cartItem.getProductName());
		return product;
	}

	private List<Product> checkIfProductsInCartAreAvailable(ShoppingCart cart) {
		List<Product> notAvailableProductsIds = new ArrayList<Product>();
		for (CartItem item : cart.getCartItems()) {
			Product product = productRepository.findProductByName(item.getProductName());
			if (item.getQuantity() > product.getQuantity()) {
				notAvailableProductsIds.add(product);
			}
		}

		return notAvailableProductsIds;
	}
	
	private void updateProductsQuantityInDataBase(Product product, CartItem item) {
		product.setQuantity(productRepository.getOne(product.getProductId()).getQuantity() - item.getQuantity());
		productRepository.saveAndFlush(product);
	}

	public List<Product> getNotAvailableProducts() {
		return notAvailableProducts;
	}
}