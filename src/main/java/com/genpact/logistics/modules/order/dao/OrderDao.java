package com.genpact.logistics.modules.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genpact.logistics.modules.order.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {
	@Query("select u from Order u where u.name = ?1") 
	Order getOrderByName(String name);
	@Query("select u from Order u where u.name like %:nn") 
	Order getOrderLikeName(@Param("nn") String name); 
	@Modifying
	@Query(value="update Order o set o.name=:newName where o.name like %:nn")
	public int findByUuidOrAge(@Param("nn") String name,@Param("newName") String newName);
}
