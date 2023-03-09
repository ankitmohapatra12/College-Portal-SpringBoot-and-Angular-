package com.exam.entity.quiz;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.exam.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="quiz_result")
public class QuizResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resultId;
	private double marksGot;
	private int questionsAttempted;
	private int correctAnswer;
	private String startTime;
	private String endTime;
	private long totalTime;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quizes;
	
}
