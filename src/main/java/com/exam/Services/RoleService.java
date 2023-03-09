package com.exam.Services;

import org.springframework.stereotype.Service;

import com.exam.entity.Role;

@Service
public interface RoleService {

	public Role getRoles(String roleName);
}
