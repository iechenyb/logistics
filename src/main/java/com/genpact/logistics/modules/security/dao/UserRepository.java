package com.genpact.logistics.modules.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.logistics.modules.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOne(int id);

    User findByUsername(String username);

}
