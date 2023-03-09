package com.exam.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.tomcat.util.json.JSONParser;
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

import com.exam.Services.QuestionService;
import com.exam.Services.QuizResultService;
import com.exam.Services.QuizService;
import com.exam.Services.UserService1;
import com.exam.entity.User;
import com.exam.entity.quiz.Question;
import com.exam.entity.quiz.Quiz;
import com.exam.entity.quiz.QuizResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private UserService1 userService;
	
	@Autowired
	private QuestionService QuestionService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuizResultService resultService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.QuestionService.addQuestion(question));
	}
	
	//get question
	@GetMapping("/{qid}")
	public Question getQuestion(@PathVariable("qid") Long questionId) {
		Question question = new Question();
		try {
		  question = this.QuestionService.getQuestion(questionId);
		}
		catch (Exception e) {
			System.out.println(question.toString());
		}
		
		return question;
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getQuestions() {
		return ResponseEntity.ok(this.QuestionService.getQuestiones());
	}
	
	
	@GetMapping("/view-result/{qid}/{userid}")
	public ResponseEntity<?> quizResults(@PathVariable("qid") Long qid,@PathVariable("userid") long userid){
		return ResponseEntity.ok(this.resultService.getQuizResultsOfUser(qid,userid));
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) throws JsonMappingException, JsonProcessingException{
		double  marksGot =0;
		Integer correctAnswers = 0;
		int attempted = 0;
		//System.out.println(questions.get(0).getUser());
		ObjectMapper mapper = new ObjectMapper();
	      // convert to json object
	      JsonNode jsonNode = mapper.readTree(questions.get(0).getUser());
	      
	    long userId=0;
	    for(JsonNode node : jsonNode){
	    
	    	userId = Long.parseLong(node.toString());
	    	break;
	    }
	    QuizResult result = new QuizResult();
	    User user = userService.getUserById(userId);
	    result.setUser(user);
		for(Question q:questions){
			Question question = this.QuestionService.get(q.getQuesId());
			if(question.getAnswer().trim().equals(q.getGivenAns().trim())) {
				//correct
				correctAnswers++;
				double marksSingle =  Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot+=marksSingle;
			}
			System.out.println(q.getGivenAns());
			if(!q.getGivenAns().trim().equals("")) {
				attempted++;
			}
			else {
				
			}
			
		};
		System.out.println(attempted);
		result.setCorrectAnswer(correctAnswers);
		result.setMarksGot(marksGot);
		result.setQuestionsAttempted(attempted);
		result.setStartTime(questions.get(0).getStartTime());
		result.setEndTime(questions.get(0).getEndTime());
		result.setTotalTime(getTotalTimeFunc(questions.get(0).getStartTime(),questions.get(0).getEndTime()));
		result.setQuizes(questions.get(0).getQuiz());
		
		return ResponseEntity.ok(resultService.saveQuizResult(result));
	}
	
	private long getTotalTimeFunc(String startTime, String endTime) {
		// TODO Auto-generated method stub
		String[] timeStart = startTime.split(",");
		String[] timeEnd = endTime.split(",");
		String start = timeStart[1].trim();
		String end = timeEnd[1].trim();
		long diff =0;
		start = start.replace("am","");
		start = start.replace("pm","");
		end = end.replace("am","").trim();
		end = end.replace("pm","").trim();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date1 = format.parse(start);
			Date date2 = format.parse(end);
			diff= date2.getTime()-date1.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(start+"          "+end);
		System.out.println(diff/60000);
		
		return diff/60000;
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.QuestionService.updateQuestion(question));
	}
	
	
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") Long questionId) {
		this.QuestionService.deleteQuestion(questionId);
	}
	
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
//		Quiz quiz = new Quiz();
//		quiz.setQid(qid);
//		Set<Question> questionsOfQuiz = this.QuestionService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);
		Quiz quiz = this.quizService.getQuiz(qid);
		List<Question> list = quiz.getQuestions();
		list.forEach((q) -> {
			q.setAnswer(null);
		});
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		Collections.shuffle(list);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		Set<Question> questionsOfQuiz = this.QuestionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
		
		
	}

}
