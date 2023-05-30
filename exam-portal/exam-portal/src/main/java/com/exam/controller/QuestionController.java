package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@GetMapping("/")
	public ResponseEntity<?> getALl() {
		return ResponseEntity.ok(questionService.findAll());
	}

	@GetMapping("/{id}")
	public Question getById(@PathVariable("id") Long id) {
		return questionService.findById(id).get();
	}

	@GetMapping("/quiz/{id}")
	public ResponseEntity<?> getQuiz(@PathVariable("id") Long id) {

		Quiz quiz = quizService.findById(id).get();
		List<Question> questions = quiz.getQuestion();
		List<Question> list = new ArrayList<>(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/quiz/all/{id}")
	public ResponseEntity<?> getQuizAdmin(@PathVariable("id") Long id) {

		Quiz quiz = quizService.findById(id).get();
		Set<Question> questions = this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questions);
	}

	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.save(question));
	}

	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.save(question));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		questionService.deleteById(id);
	}

	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		double markGot =  0;
		int correctAnwers = 0;
		int attempted = 0;
		for (Question q : questions) {
			Question question = questionService.get(q.getId());
			if (question.getAnswer().equals(q.getGivenAnwers())) {
				correctAnwers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				markGot += marksSingle;
			}
			if (q.getGivenAnwers() != null) {
				attempted++;
			}
			
		};
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("markGot", markGot);
		map.put("correctAnswer", correctAnwers);
		map.put("attempted", attempted);
		
		return ResponseEntity.ok(map);
	}
}
