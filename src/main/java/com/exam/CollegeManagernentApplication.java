package com.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.Services.UserService1;
import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;

@SpringBootApplication
public class CollegeManagernentApplication implements CommandLineRunner{
	
	
	@Autowired
	private UserService1 userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(CollegeManagernentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		User user = new User();
//		user.setFirstName("Ankit");
//		user.setLastName("Mohapatra");
//		user.setUsername("ANKIT1005");
//		user.setPassword(encoder.encode("mohapatra12345"));
//		user.setEmail("ankitmohapatra033@gmail.com");
//		
//		user.setPhone("9337053395");
//		
//		Role role = new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//		UserRole ur = new UserRole();
//		ur.setUser(user);
//		ur.setRole(role);
//		//System.out.println(ur);
//		//user.getUserRoles().add(ur);
//		userRoleSet.add(ur);
//		//user.setUserRoles(userRoleSet);
//		
//		
//		User admin = this.userService.createUser(user,userRoleSet);
//		System.out.println(admin.getUsername());
		
	}

}
