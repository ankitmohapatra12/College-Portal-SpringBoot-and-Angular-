package com.exam.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.entity.quiz.QuizResult;

@Service
public interface QuizResultService {
	
	QuizResult saveQuizResult(QuizResult quizResult);
	List<QuizResult> getQuizResultsOfUser(Long qid, long userid);
}
