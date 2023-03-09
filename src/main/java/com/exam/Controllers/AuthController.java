package com.exam.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Configuration.JwtUtils;
import com.exam.Services.UserService1;
import com.exam.Services.ServiceImpl.UserDetailsServiceImpl;
import com.exam.entity.JwtRequest;
import com.exam.entity.JwtResponse;
import com.exam.entity.User;

@RestController
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService1 userService;
	
	@GetMapping("/test")
	public String getMessage() {
		return "test";
	}
	
	//generete token 
	@PostMapping("/generate-token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		User user = userService.getUser(jwtRequest.getUsername());
		if(!user.isEnabled()) {
			System.out.println("User not enabled !!");
			return ResponseEntity.ok(new JwtResponse("User not active"));
			
		}
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		}
		catch (UsernameNotFoundException e) {
			throw new Exception("User not found !");
		}
		
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		
		
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	private void authenticate(String username,String password) throws Exception{
		try {
			
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch (DisabledException e) {
			throw new Exception("USER DISABLED");
			
		}
		catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
		
	}
	
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		User user =  (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
		if(!user.isEnabled()) {
			System.out.println("--> User not enabled !!");
			return null;
		}
		return user;
	}
	
}
