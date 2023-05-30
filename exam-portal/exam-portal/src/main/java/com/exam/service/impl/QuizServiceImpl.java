package com.exam.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepo;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{
	@Autowired
	private QuizRepo quizRepo;

	@Override
	public <S extends Quiz> S save(S entity) {
		return quizRepo.save(entity);
	}

	@Override
	public List<Quiz> findAll() {
		return quizRepo.findAll();
	}

	@Override
	public Optional<Quiz> findById(Long id) {
		return quizRepo.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		quizRepo.deleteById(id);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		return quizRepo.findBycategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		return quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category c) {
		return quizRepo.findByCategoryAndActive(c, true);
	}
	
	
}
