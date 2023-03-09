package com.exam.entity.quiz;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.exam.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quesId;
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	
	@Transient
	private String givenAns;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	@Transient
	private String user;
	
	@Transient
	private String startTime;

	@Transient
	private String endTime;
}
