package com.exam.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.quiz.Question;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;



@Service
public interface QuestionService {

	public Question addQuestion(Question Question);
	
	public Question updateQuestion(Question Question);
	
	public List<Question> getQuestiones();
	
	public Question getQuestion(Long QuestionId);
	
	public void deleteQuestion(Long QuestionId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	
	public Question get(Long questionId);

	
	
}
