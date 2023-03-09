package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exam.entity.UserImage;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

	
	@Query(value = "SELECT * FROM user_image WHERE user_id = ?1",
		    nativeQuery = true)
	UserImage findByUserId(long userid);

}
