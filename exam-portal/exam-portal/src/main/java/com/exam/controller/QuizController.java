package com.exam.controller;

import java.util.List;

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

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.service.CategoryService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.save(quiz));
	}

	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(quizService.save(quiz));
	}

	@GetMapping("/")
	public ResponseEntity<?> finall() {
		return ResponseEntity.ok(quizService.findAll());
	}

	@GetMapping("/{id}")
	public Quiz get(@PathVariable("id") Long id) {
		return quizService.findById(id).get();
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		quizService.deleteById(id);
	}
	@GetMapping("/category/{id}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("id") Long id){
		Category category = categoryService.getCategory(id);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getActiveQuizzes();
	}
	
	@GetMapping("/category/active/{id}")
	public List<Quiz> getActiveQuizzes(@PathVariable("id") Long id){
		Category category = categoryService.getCategory(id);
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
	
}
