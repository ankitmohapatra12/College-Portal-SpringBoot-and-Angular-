package com.exam.Services.ServiceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.QuestionRepository;
import com.exam.Services.QuestionService;
import com.exam.entity.User;
import com.exam.entity.quiz.Question;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;


@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;

	@Override
	public Question addQuestion(Question Question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(Question);
	}

	@Override
	public Question updateQuestion(Question Question) {
		// TODO Auto-generated method stub
		return this.questionRepo.save(Question);
	}

	@Override
	public List<Question> getQuestiones() {
		// TODO Auto-generated method stub
		List<Question> list = this.questionRepo.findAll();
		return list;
	}

	@Override
	public Question getQuestion(Long QuestionId) {
		// TODO Auto-generated method stub
		return questionRepo.findById(QuestionId).get();
	}

	@Override
	public void deleteQuestion(Long QuestionId) {
		Question question = new Question();
		question.setQuesId(QuestionId);
		this.questionRepo.delete(question);
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		Set<Question> set = this.questionRepo.findByQuiz(quiz);
		return set;
	}

	@Override
	public Question get(Long questionId) {
		// TODO Auto-generated method stub
		return this.questionRepo.getOne(questionId);
	}

	

}
