package com.genpact.logistics.modules.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genpact.logistics.modules.security.dao.UserRepository;
import com.genpact.logistics.modules.security.entity.CustomUserDetails;
import com.genpact.logistics.modules.security.entity.User;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	public User getUserById(int id) {
		return userRepository.findOne(id);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find the user '" + username + "'");
		}
		
		CustomUserDetails customUserDetails= new CustomUserDetails();
		customUserDetails.setEmail(user.getEmail());
		customUserDetails.setUserId(user.getUserId());
		customUserDetails.setPassword(user.getPassword());
		customUserDetails.setUsername(user.getUsername());
		customUserDetails.setRoles(user.getRoles());
		
		return customUserDetails;
	}
	//自定义方法，尚无用处！
	 public boolean hasRole(String role){
		 System.out.println("角色判断！");
		    return true;
	 }
}
