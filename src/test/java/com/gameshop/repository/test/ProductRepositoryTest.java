package com.gameshop.repository.test;

import com.gameshop.entity.Product;
import com.gameshop.repository.ProductRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/gameshop-configuration-tests.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@WebAppConfiguration
@EnableSpringDataWebSupport
@DatabaseSetup("/product-entries.xml")
public class ProductRepositoryTest {

	private final static int INIT_PAGE_NUMBER = 0;
	private final static int PAGE_SIZE = 5;

	@Autowired
	private ProductRepository productRepository;

	private Pageable pageable;

	@Before
	public void setUp() throws DataSetException, IOException {
		pageable = new PageRequest(INIT_PAGE_NUMBER, PAGE_SIZE);
	}

	@Test
	public void findAllShouldReturnSevenProducts() {
		List<Product> searchResults = productRepository.findAll();

		assertThat(searchResults).hasSize(7);
	}

	@Test
	public void findLatestAvailableProductsShouldReturnFiveProducts() {
		List<Product> searchResults = productRepository.findLatestAvailableProducts(pageable).getContent();

		assertTrue(checkIfProductListIsSortedByDateAdded(searchResults));
		assertThat(searchResults).hasSize(5);
	}

	@Test
	public void findLatestAvailableProductsShouldReturnOneProductOnNextPage() {
		pageable = new PageRequest(INIT_PAGE_NUMBER + 1, PAGE_SIZE);
		List<Product> searchResults = productRepository.findLatestAvailableProducts(pageable).getContent();

		assertThat(searchResults).hasSize(1);
	}

	@Test
	public void findProductsByCategoryShouldReturnFourProducts() {
		String category = "FPS";
		List<Product> searchResults = productRepository.findProductsByCategory(category, pageable).getContent();

		assertThat(searchResults).hasSize(3).extracting("productName").containsOnly("Battlefield 1",
				"Call Of Duty Infinite Warfare", "Pray");
	}

	@Test
	public void findProductsByNameShouldReturnOneProduct() {
		String productName = "Tomb";
		List<Product> searchResults = productRepository.findProductsByName(productName, pageable).getContent();

		assertThat(searchResults).hasSize(1).extracting("productName").containsOnly("Tomb Raider");
	}

	@Test
	public void findProductByNameShouldReturnSpecificProduct() {
		String productName = "Call Of Duty Infinite Warfare";
		Product foundProduct = productRepository.findProductByName(productName);

		assertNotNull(foundProduct);
		assertThat(foundProduct.getProductName()).isEqualTo(productName);
	}

	@Test
	public void addProductShouldReturnSevenProducts() {
		Product product = new Product("test", "test", BigDecimal.valueOf(100.00), 1, "/test.jpg",
				"2017-11-13 16:12:10");
		productRepository.save(product);
		List<Product> products = productRepository.findAll();
		Product newProduct = productRepository.findOne(product.getProductId());
		assertThat(products).hasSize(8);
		assertThat(newProduct.getProductId()).isEqualTo(8L);

	}

	@Test
	public void deleteProductShouldReturnSevenProducts() {
		Long productId = 1L;
		List<Product> products = productRepository.findAll();

		assertThat(products).hasSize(7);

		productRepository.delete(productId);
		products = productRepository.findAll();

		assertThat(products).hasSize(6);
	}

	private boolean checkIfProductListIsSortedByDateAdded(List<Product> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			if ((list.get(i).getDateAdded().compareTo(list.get(i + 1).getDateAdded())) > 0) {
				return false;
			}
		}
		return true;
	}

}
