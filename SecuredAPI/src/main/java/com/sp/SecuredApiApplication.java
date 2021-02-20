package com.sp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sp.entity.MyUser;
import com.sp.repository.UserRepository;

@SpringBootApplication
public class SecuredApiApplication {

	@Autowired
	private UserRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SecuredApiApplication.class, args);
	}
	
	/*@PostConstruct
	public void addUser() {
		MyUser u = new MyUser();
		u.setMyUserName("Jay");
		u.setMyUserPassword("123456");
		u.setMyUserEmail("Jay@gmail.com");
		repo.save(u);
	}*/

}
