package com.exam.Services;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.entity.quiz.Category;
import com.exam.entity.quiz.Quiz;

@Service
public interface QuizService {
	
public Quiz addQuiz(Quiz Quiz);
	
	public Quiz updateQuiz(Quiz Quiz);
	
	public List<Quiz> getQuizes();
	
	public Quiz getQuiz(Long QuizId);
	
	public void deleteQuiz(Long QuizId);

	public List<Quiz> getQuizesOfCategory(Category category);
	
	public List<Quiz> getQuizesActive();
	
	public List<Quiz> getActiveQuizesOfCategory(Category c);

}
