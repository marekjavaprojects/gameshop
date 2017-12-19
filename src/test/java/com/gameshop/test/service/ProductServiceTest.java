package com.gameshop.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.gameshop.entity.Product;
import com.gameshop.repository.OrderRepository;
import com.gameshop.repository.ProductRepository;
import com.gameshop.service.OrderServiceImpl;
import com.gameshop.service.ProductService;
import com.gameshop.service.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;
	@Mock
	private ProductRepository productRepository;

	private List<Product> products;
	private Pageable pageable;

	@Configuration
	static class ProductServiceTestContextConfiguration {
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		products = Arrays.asList(new Product("Pray", "TPP", BigDecimal.valueOf(119.99), 20, "/pray.png", "2017-12-22"),
				new Product("Battlefield 1", "FPS", BigDecimal.valueOf(109.99), 20, "/battlefield1.png", "2017-12-21"),
				new Product("Dark Souls 3", "RPG", BigDecimal.valueOf(129.99), 20, "/darksouls3.png", "2017-12-16"),
				new Product("Call of Duty Black Ops 1", "FPS", BigDecimal.valueOf(59.99), 20, "/cod1.png",
						"2017-12-19"),
				new Product("Call of Duty Black Ops 2", "FPS", BigDecimal.valueOf(79.99), 20, "/cod2.png",
						"2017-12-22"),
				new Product("Call of Duty Black Ops 3", "FPS", BigDecimal.valueOf(109.99), 20, "/cod3.png",
						"2017-12-29"));
		pageable = new PageRequest(0, 5);

	}

	@Test
	public void getLatestAddedProductsShouldReturnSixProducts() {
		when(productRepository.findLatestProducts(pageable)).thenReturn(new PageImpl<>(products));
		List<Product> latestProducts = productService.getLatestAddedProducts(pageable).getContent();
		verify(productRepository, times(1)).findLatestProducts(pageable);
		assertThat(latestProducts).hasSize(6);
	}

	@Test
	public void getLatestAvailableProductsShouldReturnEmptyListWhenProductsNotAvailable() {
		Page<Product> emptyPage = new PageImpl<>(new ArrayList<>());

		when(productRepository.findLatestAvailableProducts(pageable)).thenReturn(emptyPage);
		List<Product> latestProducts = productService.getLatestAvailableProducts(pageable).getContent();
		verify(productRepository, times(1)).findLatestAvailableProducts(pageable);
		assertThat(latestProducts).hasSize(0);
	}

	@Test
	public void getProductsShouldReturnReturnFourProducts() {
		Page<Product> page = new PageImpl<>(products);

		when(productRepository.findAll(pageable)).thenReturn(page);
		Page<Product> latestProducts = productService.getProducts(pageable);
		verify(productRepository, times(1)).findAll(pageable);
		assertThat(latestProducts).hasSize(6);
	}

	@Test
	public void getProductByIdShouldReturnSpecificProduct() {
		Product product = products.get(0);
		Long productId = 1L;

		when(productRepository.getOne(productId)).thenReturn(product);
		Product foundProduct = productService.getProductById(productId);
		verify(productRepository, times(1)).getOne(productId);
		assertThat(foundProduct.getProductName()).isEqualTo(product.getProductName());
	}

	@Test
	public void getLatestAvailableProductsShouldReturnPageWithProducts() {
		Page<Product> page = new PageImpl<>(products);

		when(productRepository.findLatestAvailableProducts(pageable)).thenReturn(page);
	}

	@Test
	public void getProductsByCategoryShouldReturnTwoProductsForCategoryFPS() {
		String categoryName = "FPS";
		Page<Product> singleCategoryProducts = getProductsByCategoryFromList(products, categoryName);

		when(productRepository.findProductsByCategory(categoryName, pageable)).thenReturn(singleCategoryProducts);
		Page<Product> foundProducts = productService.getProductsByCategory(categoryName, pageable);
		verify(productRepository, times(1)).findProductsByCategory(categoryName, pageable);
		assertThat(foundProducts.getSize()).isEqualTo(singleCategoryProducts.getSize());
	}

	@Test
	public void getProductByNameShouldReturnOneProduct() {
		String productName = "Pray";
		Product expectedProduct = products.get(0);

		when(productRepository.findProductByName(productName)).thenReturn(expectedProduct);
		Product foundProduct = productService.getProductByName(productName);
		verify(productRepository, times(1)).findProductByName(productName);
		assertThat(foundProduct.getProductName()).isEqualTo(expectedProduct.getProductName());
	}

	@Test
	public void getProductsByNameShouldReturnThreeProductsWithSamePrefix() {
		String prefix = "Call";

		when(productRepository.findProductsByName(prefix, pageable)).thenReturn(new PageImpl<>(products.subList(3, 6)));
		Page<Product> foundProducts = productService.getProductsByName(prefix, pageable);
		verify(productRepository, times(1)).findProductsByName(prefix, pageable);
		assertThat(foundProducts).hasSize(3);
		for (Product product : foundProducts) {
			assertThat(product.getProductName()).contains("Call of Duty Black Ops");
		}
	}

	@Test
	public void getProductCategoriesShouldReturnThreeCategories() {
		when(productRepository.findAll()).thenReturn(products);
		Set<String> categories = productService.getProductCategories();
		verify(productRepository, times(1)).findAll();
		assertThat(categories).hasSize(3);
	}

	private Page<Product> getProductsByCategoryFromList(List<Product> products, String category) {
		List<Product> singleCategoryProducts = new ArrayList<>();

		for (Product product : products) {
			if (product.getCategory().equals(category)) {
				singleCategoryProducts.add(product);
			}
		}

		return new PageImpl<>(singleCategoryProducts);
	}

}
