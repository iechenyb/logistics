package com.genpact.logistics.modules.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ms_security_user"/* ,schema="" */)
public class User {
	
	@Id
	@GeneratedValue
    private Integer userId;

    private String username;

    private String password;

    private String email;
    
    //用户与角色的关联关系
    //@ManyToMany注释表示Teacher是多对多关系的一端。
    //@JoinTable描述了多对多关系的数据表关系。name属性指定中间表名称，joinColumns定义中间表与Role表的外键关系。
    //中间表ms_security_user_role的user_id列是user表的主键列对应的外键列，inverseJoinColumns属性定义了中间表与另外一端(role)的外键关系。
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ms_security_user_role", 
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "userId") }, 
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "roleId") })
    @JsonIgnore
    private List<Role> roles = new ArrayList<Role>();

   

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


    
}