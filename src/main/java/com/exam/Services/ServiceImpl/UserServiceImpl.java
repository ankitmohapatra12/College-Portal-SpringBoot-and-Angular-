package com.exam.Services.ServiceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Exceptions.ResourceNotFoundException;
import com.exam.Repository.RoleRepository;
import com.exam.Repository.UserImageRepository;
import com.exam.Repository.UserRepository;
import com.exam.Services.UserService1;
import com.exam.entity.User;
import com.exam.entity.UserImage;
import com.exam.entity.UserRole;

@Service
public class UserServiceImpl implements UserService1{
	
	@Autowired
    private UserRepository userRepo;
	
	@Autowired
	private UserImageRepository imageRepo;
    
	@Autowired
    private RoleRepository roleRepo;
	

	//C R E A T I N G       U S E R
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User checkUser = this.userRepo.findByUsername(user.getUsername());
		if(checkUser!=null) {
			throw new Exception("User is already present !!");
		}
		else {
			System.out.println(userRoles);
			System.out.println(user);
			//user.setUserRoles(userRoles);
			for(UserRole ur:userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			System.out.println(user.toString());
			checkUser = this.userRepo.save(user);
			
		}
		return checkUser;
	}


	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepo.findByUsername(username);
	}


	@Override
	public void deleteUser(Long userId) {
		this.userRepo.deleteById(userId);
		
	}


	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found !"));
	}


	@Override
	public User updateUser(User getUser) {
		// TODO Auto-generated method stub
		
		return userRepo.saveAndFlush(getUser);
	}


	@Override
	public Boolean verifyUser(User user) {
		// TODO Auto-generated method stub
		User userdata =  userRepo.findById(user.getId()).get();
		if(user.getSecretQuestion().equals(userdata.getSecretQuestion())) {
			if(user.getSecretAnswer().equals(userdata.getSecretAnswer())){
				return true;
			}
		}
		return false;
	}


	@Override
	public UserImage saveProfileImage(UserImage userImage) {
		// TODO Auto-generated method stub
		return imageRepo.saveAndFlush(userImage);
	}


	@Override
	public UserImage getImageData(long userid) {
		// TODO Auto-generated method stub
		return imageRepo.findByUserId(userid);
	}


	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = userRepo.findAll();
		System.out.println(users.get(1).getAcademicDetail());
		return users;
	}

}
