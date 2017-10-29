package com.gameshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p ORDER BY dateAdded")
	public List<Product> findFourLastProducts();

	@Query("SELECT p FROM Product p WHERE p.category= :category")
	public List<Product> findProductsByCategory(@Param("category") String category);

	@Query("SELECT p FROM Product p WHERE p.productName LIKE :productName%")
	public List<Product> searchProductsByName(@Param("productName") String productName);

	@Query("SELECT p FROM Product p WHERE p.productName = :productName")
	public Product findProductByName(@Param("productName") String productName);
}
