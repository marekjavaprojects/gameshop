package com.gameshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p ORDER BY p.dateAdded DESC")
	public List<Product> findLatestProducts();

	@Query("SELECT p FROM Product p ")
	public Page<Product> findAllProducts(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.quantity > 0 ORDER BY dateAdded ASC")
	public Page<Product> findLatestAvailableProducts(Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.category= :category AND p.quantity > 0")
	public Page<Product> findProductsByCategory(@Param("category") String category, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.productName LIKE :productName% AND p.quantity > 0")
	public Page<Product> findProductsByName(@Param("productName") String productName, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.productName = :productName")
	public Product findProductByName(@Param("productName") String productName);
}
