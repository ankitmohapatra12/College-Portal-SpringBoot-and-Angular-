package com.exam.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.User;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;


@Repository
public interface ResultRepository extends JpaRepository<QuizResult, Long> {

	

	List<QuizResult> findByQuizesAndUser(Quiz quiz, User user);

}
