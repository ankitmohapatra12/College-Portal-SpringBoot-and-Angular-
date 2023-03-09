package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.Academic.AcademicDetails;

@Repository
public interface AcademicRepository extends JpaRepository<AcademicDetails, Long> {

	AcademicDetails findByUserId(long id);

}
