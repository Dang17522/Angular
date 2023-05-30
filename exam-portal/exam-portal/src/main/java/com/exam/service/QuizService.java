package com.exam.service;

import java.util.List;
import java.util.Optional;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;

public interface QuizService {

	void deleteById(Long id);

	Optional<Quiz> findById(Long id);

	List<Quiz> findAll();

	<S extends Quiz> S save(S entity);

	public List<Quiz> getQuizzesOfCategory(Category category);
	
	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
