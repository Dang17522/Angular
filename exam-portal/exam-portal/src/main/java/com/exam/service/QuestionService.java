package com.exam.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

public interface QuestionService {

	Question getById(Long id);

	Optional<Question> findById(Long id);

	List<Question> findAll();

	<S extends Question> S save(S entity);

	void deleteById(Long id);

	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public Question get(Long id);
}
