package com.genpact.logistics.modules.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.logistics.modules.demo.dao.DemoCacheRepository;
import com.genpact.logistics.modules.demo.entity.DemoCache;

@Service
public class DemoCacheService {

	@Autowired
	private DemoCacheRepository demoCacheRepository;
	
//	@CachePut(value = "demoCache", key = "#demo.id")
	public DemoCache save(DemoCache demo) {
		DemoCache s = demoCacheRepository.save(demo);
		return s;
	}
	
//	@CacheEvict(value = "demoCache")
	public void delete(Integer id) {
		demoCacheRepository.delete(id);
	}
	
//	@Cacheable(value = "demoCache", key = "#demo.id")
	public DemoCache findOne(int id) {
		DemoCache s = demoCacheRepository.findOne(id);
		return s;
	}
//	@Cacheable(value = "demoCache")
	public List<DemoCache> findAll() {
		return demoCacheRepository.findAll();
	}

}
