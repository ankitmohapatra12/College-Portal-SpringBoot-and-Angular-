package com.exam.entity.Academic;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.exam.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long academicId;
	
	private String course;
	
	private String dept;
	
	private String section;
	
	private String registrationNumber;
	
	private String startBatch;
	
	private String endBatch;
	
	private String hodname;
	
	private String blockNumber;
	
	private String roomNumber;
	
	private String admissionDate;
	
	@Transient
	private long userid;
	
	private boolean active = false;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
}
