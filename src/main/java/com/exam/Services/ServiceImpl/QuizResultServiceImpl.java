package com.exam.Services.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.ResultRepository;
import com.exam.Services.QuizResultService;
import com.exam.entity.User;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;

@Service
public class QuizResultServiceImpl implements QuizResultService {
	
	
	@Autowired
	private ResultRepository resultRepo;

	@Override
	public QuizResult saveQuizResult(QuizResult quizResult) {
		// TODO Auto-generated method stub
		return resultRepo.save(quizResult);
	}
	
	@Override
	public List<QuizResult> getQuizResultsOfUser(Long qid, long userid) {
		// TODO Auto-generated method stub
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		User user = new User();
		user.setId(userid);
		return resultRepo.findByQuizesAndUser(quiz,user);
	}

}
