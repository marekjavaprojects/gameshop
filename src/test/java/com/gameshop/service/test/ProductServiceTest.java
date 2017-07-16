package com.gameshop.service.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.gameshop.dao.ProductDAO;
import com.gameshop.entity.Product;
import com.gameshop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:gameshop-configuration-tests.xml"})
@WebAppConfiguration
public class ProductServiceTest {

	@Mock
	ProductDAO productDAOMock;

	@Autowired
	ProductService productService;

	@Before
	public void setup() {		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnAllProducts() {
		List<Product> listOfAllProducts = new ArrayList<Product>(Arrays.asList(
										  new Product(1,"Battlefield1","FPS",30.0,100,"/battlefield1.png","2017-05-16 23:37:16"),
										  new Product(2,"Call Of Duty Infinite Warfare","FPS",20.0,100,"/codiw.png","2017-05-16 23:37:16"),
										  new Product(3,"Dark Souls 3","RPG",20.0,100,"/darksouls3.png","2017-05-16 23:37:16"),
										  new Product(4,"For Honor","TPP",20.0,100,"/forhonor.png","2017-05-16 23:37:16"),
									      new Product(5,"Pray","FPS",20.0,100,"/pray.png","2017-05-16 23:37:16")));
		given(productDAOMock.getProducts()).willReturn(listOfAllProducts);		
		
		List<Product> products = productService.getProducts();
		assertThat(products.size(), is(5));
	}

}
