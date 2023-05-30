package com.exam.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepo;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public <S extends Question> S save(S entity) {
		return questionRepo.save(entity);
	}

	@Override
	public List<Question> findAll() {
		return questionRepo.findAll();
	}

	@Override
	public Optional<Question> findById(Long id) {
		return questionRepo.findById(id);
	}

	@Override
	public Question getById(Long id) {
		return questionRepo.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		questionRepo.deleteById(id);
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		return questionRepo.findByQuiz(quiz);
	}

	@Override
	public Question get(Long id) {
		return this.questionRepo.getOne(id);
	}
	
	
}
