package com.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gameshop.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

}
