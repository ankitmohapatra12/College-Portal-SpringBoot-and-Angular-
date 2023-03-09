package com.exam.Services.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.RoleRepository;
import com.exam.Services.RoleService;
import com.exam.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getRoles(String roleName) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("USER");
		Role fetchedRole =  new Role();
		try {
			fetchedRole = roleRepository.findByRoleName(roleName);
		}
		catch (Exception e) {
			
		}
		return fetchedRole!=null?fetchedRole:role;
	}

}
