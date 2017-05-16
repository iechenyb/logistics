package com.genpact.logistics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genpact.logistics.modules.security.dao.UserRepository;
import com.genpact.logistics.modules.security.entity.Role;
import com.genpact.logistics.modules.security.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="application.properties")
public class LogisticsApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Test
	public void contextLoads() {
		User user = userRepository.findByUsername("voltest");//dao层如何实现
		for(Role role:user.getRoles()){
			System.out.println(role.getName()+","+role.getType());
		}
	}

}
