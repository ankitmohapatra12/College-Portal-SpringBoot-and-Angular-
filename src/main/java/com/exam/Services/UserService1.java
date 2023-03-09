package com.exam.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserImage;
import com.exam.entity.UserRole;

@Service
public interface UserService1 {

	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	public User getUser(String username);
	
	public void deleteUser(Long userId);

	public User getUserById(Long userId);

	public User updateUser(User getUser);

	public Boolean verifyUser(User user);

	public UserImage saveProfileImage(UserImage userImage);

	public UserImage getImageData(long userid);

	public List<User> getAllUsers();
	
}
