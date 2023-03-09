package com.exam.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.User;
import com.exam.entity.quiz.Question;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);


	

}
