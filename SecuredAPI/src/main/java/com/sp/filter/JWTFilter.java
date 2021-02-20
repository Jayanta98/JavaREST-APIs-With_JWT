package com.sp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sp.jwtutil.JwtUtil;
import com.sp.service.CustomUserDetailsService;

@Component
public class JWTFilter extends OncePerRequestFilter {

	 @Autowired
	    private JwtUtil jwtUtility;

	    @Autowired
	    private CustomUserDetailsService userService;


	    @Override
	    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

	        String authorizationHeader = httpServletRequest.getHeader("Authorization");

	        String token = null;
	        String userName = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            token = authorizationHeader.substring(7);
	            userName = jwtUtility.extractUsername(token);
	        }

	        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	        	//CustomService call LoadUserByName method and give [User object <---CustomUser from database]
	        	
	            UserDetails userDetails = userService.loadUserByUsername(userName);
	            
	            System.out.println("Loading :"+userName);

	            //then validate using (token ,User) coming from the service method
	            if (jwtUtility.validateToken(token, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        filterChain.doFilter(httpServletRequest, httpServletResponse);
	    }
}
