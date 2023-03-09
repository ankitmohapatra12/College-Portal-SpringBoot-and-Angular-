package com.exam.Services.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.AcademicRepository;
import com.exam.Repository.UserRepository;
import com.exam.Services.AcademicService;
import com.exam.entity.User;
import com.exam.entity.Academic.AcademicDetails;

@Service
public class AcademicServiceImpl implements AcademicService {
	
	@Autowired
	private AcademicRepository academicRepository;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public AcademicDetails addAcademicData(AcademicDetails academicDetails) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(academicDetails.getUserid()).get();
		academicDetails.setUser(user);
		return academicRepository.save(academicDetails);
	}

	@Override
	public AcademicDetails getAcademicDataOfUser(long id) {
		// TODO Auto-generated method stub
		AcademicDetails details = academicRepository.findByUserId(id);
		return details;
	}

	@Override
	public AcademicDetails updateAcademicData(AcademicDetails academicDetails) {
		try {
			User user = userRepo.findById(academicDetails.getUserid()).get();
			System.out.println(user);
			academicDetails.setUser(user);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return academicRepository.save(academicDetails);
	}
	
	

	
}
