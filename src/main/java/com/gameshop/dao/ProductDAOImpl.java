package com.gameshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gameshop.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts() {

		Session currentSession = sessionFactory.getCurrentSession();

		// create a query .. sort by last name
		Query<Product> query = currentSession.createQuery("from Product", Product.class);
		// execute query and get result list
		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	public List<Product> getFourLatestProducts() {

		Session currentSession = sessionFactory.getCurrentSession();

		// create a query .. sort by last name
		Query<Product> queryForLatestFourProducts = currentSession.createQuery("from Product order by dateAdded", Product.class);
		queryForLatestFourProducts.setMaxResults(4);
		// execute query and get result list
		List<Product> latestFourProducts = queryForLatestFourProducts.getResultList();

		return latestFourProducts;
	}

	@Override
	public Product getProductById(int productId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Product product = currentSession.get(Product.class, productId);

		return product;
	}
	
	@Override
	public List<Product> getProductsByCategory(String category) {

		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "from Product where category=:category";
		Query<Product> queryForProductsByCategory = currentSession.createQuery(hql, Product.class);
		queryForProductsByCategory.setParameter("category",category);

		List<Product> productsByCategory = queryForProductsByCategory.getResultList();
		
		return productsByCategory;
	}
	
	@Override
	public List<Product> searchProductsByName(String productName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "from Product where productName like :productName ";
		Query<Product> queryForProductsSearchedByProductName = currentSession.createQuery(hql, Product.class);
		queryForProductsSearchedByProductName.setParameter("productName", productName + "%");

		List<Product> productsByCategory = queryForProductsSearchedByProductName.getResultList();

		return productsByCategory;
		
		
		
	}


}
