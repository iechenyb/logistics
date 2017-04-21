package com.genpact.logistics.modules.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.logistics.modules.demo.entity.DemoCache;
import com.genpact.logistics.modules.demo.service.DemoCacheService;



//表示返回数据是json格式
@RestController
@RequestMapping("/demo")
public class DemoCacheController {
	@Autowired
	private DemoCacheService demoCacheService;

	
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
	public ResponseEntity<?> put(@RequestBody DemoCache demoCache) {
		DemoCache demo = demoCacheService.save(demoCache);
		if (demo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")//是否具备其中一个角色
	public ResponseEntity<?> cacheable(@PathVariable Integer id) {
		DemoCache demo = demoCacheService.findOne(id);
		if (demo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}

	@RequestMapping("/{id}/delete")
	public ResponseEntity<?> evit(@PathVariable Integer id) {
		demoCacheService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public  ResponseEntity<?>  findAll() {
		List<DemoCache> list = demoCacheService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
