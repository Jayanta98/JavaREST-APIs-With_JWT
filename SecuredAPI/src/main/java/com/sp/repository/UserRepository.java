package com.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

	
	MyUser findUserBymyUserName(String name);
}
