package com.genpact.logistics.modules.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genpact.logistics.modules.order.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
	@Query("select u from Order u where u.name = ?1") 
	Order getOrderByName(String name); 
}
