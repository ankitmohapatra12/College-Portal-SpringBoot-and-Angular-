package com.exam.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.Services.UserService1;
import com.exam.Services.ServiceImpl.UserDetailsServiceImpl;
import com.exam.entity.User;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private UserService1 userService1;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	    final String requestTokenHeader = request.getHeader("Authorization");
	    System.out.println(requestTokenHeader);
	    String username = null;
	    String jwtToken = null;
	    
	    if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
	    	
	    	try {
	    		jwtToken = requestTokenHeader.substring(7);
	    		username = jwtUtil.extractUsername(jwtToken);
	    		
	    	}catch (ExpiredJwtException e) {
				System.out.println("JWT token expired !!");
			}
	    	catch (Exception e) {
				System.out.println(e);
			}
	    	
	    }
	    else {
	    	
	    	System.out.println("Invalid Token , not starts with Bearer");
	    }
	    
	    
	    if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    	final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	    	if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
	    		
	    		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	    		usernamePasswordAuthenticationFilter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationFilter);
	    		
	    	}
	    	else {
	    		
	    		System.out.println("Token is not valid !!");
	    	}
	    	
	    	
	    }
	    
	    filterChain.doFilter(request, response);
		
	}

}
