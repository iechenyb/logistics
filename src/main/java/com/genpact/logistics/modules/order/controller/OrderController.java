package com.genpact.logistics.modules.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.logistics.modules.order.entity.Order;
import com.genpact.logistics.modules.order.service.OrderService;
//表示返回数据是json格式
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	DefaultMethodSecurityExpressionHandler X;
	 /*@Value("#{orderController.initName()}")
	 public String name;
	 public String initName(){
	    	return "chenyb";
	 }*/
	/**
	 * 方法名:put
	 * 描    述: method=RequestMethod.PUT 限定请求的方法
	 * 		  @RequestBody 限定请求的参数是json格式 如果去掉普通表单提交数据
	 * 返回值:SysUser
	 * 参    数:@param demoCache
	 * 参    数:@return
	 * 作    者:710009498
	 * 时    间:Apr 10, 2017 9:22:37 AM
	 */
	@PutMapping(value = "/put")
	public ResponseEntity<?> put(@RequestBody Order demoCache) {
		Order demo = orderService.save(demoCache);
		if (demo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}
	//@PreAuthorize("1==1")
	@PutMapping(value = "/put1")
	public ResponseEntity<?> test1() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	//@PreAuthorize("1==2")
	@PutMapping(value = "/put2")
	public ResponseEntity<?> test2() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	//@PreAuthorize("orderService.name==orderService.name")
	@PutMapping(value = "/put4")
	public ResponseEntity<?> test4() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	//@PreAuthorize("orderService.getBoolean()")
	@PutMapping(value = "/put5")
	public ResponseEntity<?> test5() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	//@PreAuthorize("getBoolean()")
	@PutMapping(value = "/put6")
	public ResponseEntity<?> test6() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")//是否具备其中一个角色
	public ResponseEntity<?> cacheable(@PathVariable Integer id) {
		Order demo = orderService.findOne(id);
		if (demo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}
	@GetMapping("/{id1}/{id2}")
	//@PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")//是否具备其中一个角色
	public ResponseEntity<?> cacheable(@PathVariable int id1,@PathVariable int id2) {
		Order demo = orderService.findOne(id1,id2);
		if (demo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}
	   public boolean hasRole(String role){
		    return true;
	   }
	@RequestMapping("/{id}/delete")
	public ResponseEntity<?> evit(@PathVariable Integer id) {
		orderService.delete(id);
		//SpringSecurityUtils x;
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public  ResponseEntity<?>  findAll() {
		List<Order> list = orderService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/byName")
	public  ResponseEntity<?>  findAllBase(String name) {
		Order o = orderService.getByName(name);
		return new ResponseEntity<>(o, HttpStatus.OK);
	}
}
