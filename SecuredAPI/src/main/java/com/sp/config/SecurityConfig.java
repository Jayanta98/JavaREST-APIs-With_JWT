package com.sp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sp.filter.JWTFilter;
import com.sp.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig extends  WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTFilter myfilter;
	
	
	//Authentication Use My CustomUserDetailsSErvice ----that call loadUserByUserDetails via DataBase
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	
	
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	 @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 
	 
	 
	
	
	//Authorization Setting 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/authenticate","/")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
http.addFilterBefore(myfilter, UsernamePasswordAuthenticationFilter.class);
	}

}
