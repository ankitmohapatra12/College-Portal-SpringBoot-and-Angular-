package com.exam.Services.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.Repository.QuizRepository;
import com.exam.Services.QuizService;
import com.exam.entity.quiz.Category;
import com.exam.entity.quiz.Quiz;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private QuizRepository quizRepo;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public List<Quiz> getQuizes() {
		// TODO Auto-generated method stub
		List<Quiz> set = new ArrayList<>(this.quizRepo.findAll());
		return set;
	}

	@Override
	public Quiz getQuiz(Long QuizId) {
		
		return this.quizRepo.findById(QuizId).get();
	}

	@Override
	public void deleteQuiz(Long QuizId) {
		this.quizRepo.deleteById(QuizId);;
	}

	@Override
	public List<Quiz> getQuizesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategory(category);
		
	}

	@Override
	public List<Quiz> getQuizesActive() {
		// TODO Auto-generated method stub
		return this.quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategoryAndActive(c, true);
	}

}
