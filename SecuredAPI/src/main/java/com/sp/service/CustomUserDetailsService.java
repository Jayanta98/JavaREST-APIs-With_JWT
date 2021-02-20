package com.sp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sp.entity.MyUser;
import com.sp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository repo;
	
	//Repo--->Database---->bring CustomUser---->new User() this is spring user in memory
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myuser= repo.findUserBymyUserName(username);
		if(myuser != null) {
			return new User(myuser.getMyUserName(),myuser.getMyUserPassword(),new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User Not Found with that Name:"+username);
		}
		
		
	}

}
