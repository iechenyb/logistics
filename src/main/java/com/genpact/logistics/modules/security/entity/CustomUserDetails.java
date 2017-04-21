package com.genpact.logistics.modules.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 继承自User，包装了角色信息的User扩展类
 * 实现自spring-security提供的UserDetails，并实现接口
 * 用于映射一个用户的多权限查询结果集
 */
public class CustomUserDetails extends User implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //定义权限集合
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //当前用户的角色信息集合
        //添加角色信息到权限集合 lambda表达式 java
        /*getRoles().stream()
        .map(role->new SimpleGrantedAuthority(role.getType()))
        .forEach(o-> authorities.add(o));*/
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
        	authorities.add(new SimpleGrantedAuthority(role.getType()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
