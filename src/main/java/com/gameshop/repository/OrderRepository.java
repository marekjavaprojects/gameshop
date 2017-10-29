package com.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gameshop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	

}
