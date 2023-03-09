package com.exam.entity.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qid;
	private String title;
	private String description;
	private String maxMarks;
	private String numberOfQuestions;
	private String maxTime;
	private boolean active = false;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Question> questions = new ArrayList<>();
	

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="quizes")
	@JsonIgnore
	private List<QuizResult> quizResults = new ArrayList<>();
}
