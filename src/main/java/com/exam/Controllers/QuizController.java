package com.exam.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Services.QuizService;
import com.exam.entity.quiz.Category;
import com.exam.entity.quiz.Quiz;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {


	@Autowired
	private QuizService quizService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz Quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(Quiz));
	}
	
	//get Quiz
	@GetMapping("/{QuizId}")
	public Quiz getQuiz(@PathVariable("QuizId") Long QuizId) {
		return this.quizService.getQuiz(QuizId);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getQuizes() {
		return ResponseEntity.ok(this.quizService.getQuizes());
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz Quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(Quiz));
	}
	
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizesOfCategory(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getQuizesOfCategory(category);
	}
	
	
	@DeleteMapping("/{QuizId}")
	public void deleteQuiz(@PathVariable("QuizId") Long QuizId) {
		this.quizService.deleteQuiz(QuizId);
	}
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getQuizesActive();
	}
	
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable("cid") Long cid){
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getActiveQuizesOfCategory(category);
	}
	
	
	
	

}
