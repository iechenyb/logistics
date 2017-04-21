package com.genpact.logistics.modules.order.service;

import java.util.List;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.genpact.logistics.modules.order.dao.OrderDao;
import com.genpact.logistics.modules.order.entity.Order;
import com.genpact.logistics.modules.security.entity.User;

@Service("orderService")
public class OrderService {
    public String name="chenyb";
	@Autowired
	private OrderDao demoCacheRepository;
	public boolean getBoolean(){
		Assert x;
		return true;
	}
/*	@Autowired
	private SimpleJpaRepository<?, ?> simpleJpaRepository;*/
//	@CachePut(value = "demoCache", key = "#demo.id")
	public Order save(Order demo) {
		Order s = demoCacheRepository.save(demo);
		return s;
	}
	
//	@CacheEvict(value = "demoCache")
	public void delete(Integer id) {
		demoCacheRepository.delete(id);
	}
	
//	@Cacheable(value = "demoCache", key = "#demo.id")
	//@PreAuthorize("#id=='chenyb'")
	public Order findOne(int id) {
		Order s = demoCacheRepository.findOne(id);
		return s;
	}
	//@PostAuthorize("#id1==#id2")
	public Order findOne(int paraId1,int paraId2) {
		Order s = demoCacheRepository.findOne(paraId1);
		return s;
	}
//	@Cacheable(value = "demoCache")
	public List<Order> findAll() {
		return demoCacheRepository.findAll();
	}
	public Order getByName(String name) {
		return demoCacheRepository.getOrderByName(name);
	}
	/**
	 * 可以使用SpEL方法有四种：
		@PreAuthorize： 在方法调用前，基于表达式计算结果来限制方法访问
		@PostAuthorize: 允许方法调用，但是如果表达式结果为fasle则抛出异常
		@PostFilter :允许方法调用，但必须按表达式过滤方法结果。
		@PreFilter:允许方法调用，但必须在进入方法前过滤输入值
	 * @param user
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addUser(User user){
	   //如果具有权限 ROLE_ADMIN 访问该方法
	}

	//returnObject可以获取返回对象user，判断user属性username是否和访问该方法的用户对象的用户名一样。不一样则抛出异常。
	@PostAuthorize("returnObject.user.username==principal.username")
	public User getUser(int userId){
	   //允许进入
	    return null;    
	}

	//将结果过滤，即选出性别为男的用户
	@PostFilter("returnObject.user.sex=='男' ")
	public List<User> getUserList(){
	   //允许进入
	    return null;    
	}
}
