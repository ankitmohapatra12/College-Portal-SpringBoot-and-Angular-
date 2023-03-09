package com.exam.Services;

import org.springframework.stereotype.Service;

import com.exam.entity.Academic.AcademicDetails;

@Service
public interface AcademicService {

	AcademicDetails addAcademicData(AcademicDetails academicDetails);

	AcademicDetails getAcademicDataOfUser(long id) throws Exception;

	AcademicDetails updateAcademicData(AcademicDetails academicDetails);

}
