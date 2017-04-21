package com.genpact.logistics.modules.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.logistics.modules.demo.entity.DemoCache;

public interface DemoCacheRepository extends JpaRepository<DemoCache, Integer> {
	
}
