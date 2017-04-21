package com.genpact.logistics.modules.order.aspect;

import org.springframework.stereotype.Component;

@Component
public class OrderAspectModel {
	public long add(int a,int b){
		return a+b;
	}
}	
