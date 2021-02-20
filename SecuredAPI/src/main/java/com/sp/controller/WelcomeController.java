package com.sp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.dto.JwtRequest;
import com.sp.dto.JwtResponse;
import com.sp.jwtutil.JwtUtil;

@RestController
public class WelcomeController {

	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
@GetMapping("/")
public String welcome() {
		return "Welcome to Secured API";
	}


@GetMapping("/data")
public String Data() {
		return "Welcome to Secured Data Section";
	}
	
	
/*
 * AuthenticationManager.authenticate(--> this call UserNamePasswordAuthenticateToken(name,pass)) if its all done then 
 * use jwtutil.generateToken() 
 */


	@PostMapping("/authenticate")
	public String doGetTogen(@RequestBody JwtRequest req) throws Exception {
		try {
			System.out.println(req.getMyName()+"----"+req.getMyPass());
			 authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(req.getMyName(),req.getMyPass())
	            );
			
		} catch (Exception e) {
			throw new Exception("Invalid Credential  "+req.getMyName()+"---"+req.getMyPass());
		}
		
		String tokenData = jwtutil.generateToken(req.getMyName());
		return tokenData;
		
	}
}
