package com.exam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Repository.UserRepository;
import com.exam.Services.AcademicService;
import com.exam.entity.User;
import com.exam.entity.Academic.AcademicDetails;

@RestController
@RequestMapping("/academic")
@CrossOrigin("*")
public class AcademicDataController {
	
	@Autowired
	private AcademicService academicService;

	
	@PostMapping("/create")
	public ResponseEntity<?> addAcademicData(@RequestBody AcademicDetails academicDetails){
		
		return ResponseEntity.ok(academicService.addAcademicData(academicDetails));
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> getAcademicDataOfUser(@PathVariable("userid") long id) throws Exception{
		AcademicDetails details =  academicService.getAcademicDataOfUser(id);
		details.setUser(null);
		
		return ResponseEntity.ok(details);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateAcademicDataOfUser(@RequestBody AcademicDetails academicDetails){
		System.out.println(academicDetails);
		return ResponseEntity.ok(academicService.updateAcademicData(academicDetails));
	}
}
