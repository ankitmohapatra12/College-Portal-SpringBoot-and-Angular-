package com.exam.Controllers;

import java.awt.PageAttributes.MediaType;
import com.itextpdf.text.pdf.codec.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exam.Services.RoleService;
import com.exam.Services.UserService1;
import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserImage;
import com.exam.entity.UserRole;

import lombok.val;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Value("${file.profile.image}")
	private String profileImage;
	
	@Autowired
	private UserService1 userService;
	
	@Autowired
	private RoleService roleService;
	
	private static final String role_user="USER";
	
	@Autowired
	private BCryptPasswordEncoder encoderer;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setPassword(this.encoderer.encode(user.getPassword()));
		Set<UserRole> roles =  new HashSet<>();
		UserRole userRole = new UserRole();
		Role role = new Role();
		
		role = roleService.getRoles(role_user);
		
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		user.setEnabled(false);
		return this.userService.createUser(user,roles);
	}
	
	
	@PutMapping("/toggle-enabled")
	public String toggleEnabled(@RequestParam("active") boolean active, @RequestParam("userid") long userid) throws Exception {
		User user = userService.getUserById(userid);
		System.out.println(active);
		if(user!=null) {
			if(active) {
				user.setEnabled(false);
			}
			else {
				user.setEnabled(true);
			}
			user = userService.updateUser(user);
			System.out.println(user.isEnabled());
		}
		
		return "";
	}
	
	@SuppressWarnings({ "unused", "null" })
	@PutMapping(value={"/upload-image"},consumes = "multipart/form-data")
	public UserImage addUserImage(@RequestParam("file") MultipartFile image,@RequestParam("user") long userid) throws Exception {
		boolean flag= true;
		File destPath = null;
		String extension = "";
		String profileImageName="";
		
		UserImage fetchImage = new UserImage();
		try {
		fetchImage = userService.getImageData(userid);
		flag = false;
		}catch (Exception e) {
			flag= true;
			
		}
		
		
		UserImage userImage = new UserImage();
		if(image!=null && fetchImage==null) {
			
			
				User user = userService.getUserById(userid);
				destPath=new File(profileImage);
				if(!destPath.exists()){
					destPath.mkdirs();
				}
				extension=FilenameUtils.getExtension(image.getOriginalFilename());
				profileImageName = "profile"+"_"+user.getUsername()+"_"+userid+"."+extension;
				userImage.setImageName(profileImageName);
				FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + profileImageName));
				
				userImage.setImageSize(image.getSize());
				userImage.setUploadDate(new Date());
				userImage.setUser(user);
			
			
			userService.saveProfileImage(userImage);
		} 
		else if(image!=null && fetchImage!=null) {
			User user = userService.getUserById(userid);
			destPath=new File(profileImage);
			if(!destPath.exists()){
				destPath.mkdirs();
			}
			File file
            = new File(destPath + File.separator + userImage.getImageName());
			file.delete();
			extension=FilenameUtils.getExtension(image.getOriginalFilename());
			profileImageName = "profile"+"_"+user.getUsername()+"_"+userid+"."+extension;
			userImage.setImageName(profileImageName);
			FileCopyUtils.copy(image.getBytes(),new File(destPath + File.separator + profileImageName));
			userImage.setImageId(fetchImage.getImageId());
			userImage.setImageSize(image.getSize());
			userImage.setUploadDate(new Date());
			userImage.setUser(user);
			userService.saveProfileImage(userImage);
		}
		else {
			throw new Exception("Image is null");
		}
	
		return userImage;
	}
	
	
	
	@GetMapping("/get-profile-image/{userid}")
	public UserImage addUserImage(@PathVariable("userid") long userid) throws Exception {
		File destPath=new File(profileImage);
		UserImage fetchImage = userService.getImageData(userid);
		if(fetchImage == null) {
			return new UserImage();
		}
		String imageUrl = destPath + File.separator + fetchImage.getImageName();
		String extension = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
		File f = new File(imageUrl);
		if(f.exists()) {
			String  extnValue = "image/" + extension;
			String encodstring = encodeFileToBase64Binary(f);
			String base64Image = "data:" + extnValue + ";base64," + encodstring;
			fetchImage.setImageName(base64Image);
		}
		
		return fetchImage;
	}
	
	private static String encodeFileToBase64Binary(File file) throws Exception {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		fileInputStreamReader.close();
		return new String(java.util.Base64.getEncoder().encodeToString(bytes));
	}
	
	
	@PostMapping("/verify-secret-key")
	public Boolean verifySecretAnswer(@RequestBody User user) throws Exception {
	
		return this.userService.verifyUser(user);
	}
	
	//get user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return this.userService.getUser(username);
	}
	
	@GetMapping("/all-users")
	public List<User> getUser() {
		return this.userService.getAllUsers();
	}
	
	//delete mapping
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
	
	
	@PutMapping("/update/{userId}")
	public User updateUser(@PathVariable("userId") long userId,@RequestBody User user) throws Exception {
		System.out.println(userId);
		User getUser = this.userService.getUserById(userId);
		if(user.getEmail()!=null) {
			getUser.setEmail(user.getEmail());
		}
		if(user.getFirstName()!=null) {
			getUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null) {
			getUser.setLastName(user.getLastName());
		}
		if(user.getUsername()!=null || user.getUsername().equals("")) {
			getUser.setUsername(user.getUsername());
		}
		if(user.getPassword()!=null || user.getPassword().equals("")) {
			getUser.setPassword(encoderer.encode(user.getPassword()));
		}
		if(user.getPhone()!=null) {
			getUser.setPhone(user.getPhone());
		}
		if(user.getAddress()!=null) {
			getUser.setAddress(user.getAddress());
		}
		if(user.getPincode()!=null) {
			getUser.setPincode(user.getPincode());
		}
		if(user.getSecretQuestion()!=null) {
			getUser.setSecretQuestion(user.getSecretQuestion());
		}
		if(user.getSecretAnswer()!=null) {
			getUser.setSecretAnswer(user.getSecretAnswer());
		}
		return this.userService.updateUser(getUser);
	}
	
	
}
