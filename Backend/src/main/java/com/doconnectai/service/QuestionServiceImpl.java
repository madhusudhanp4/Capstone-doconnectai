package com.doconnectai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;
import com.doconnectai.mapper.QuestionMapper;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionRepo qstnRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public QuestionDto addQuestion(QuestionDto question) {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepo.findByEmail(email);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		Question qstn = QuestionMapper.toEntity(question, user);

		Question saved = qstnRepo.save(qstn);

		return QuestionMapper.toDto(saved);
	}

	@Override
	public List<QuestionDto> getAllQuestion() {

		List<Question> questions = qstnRepo.findAll();

		return questions.stream().map(QuestionMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public QuestionDto getQuestionById(Integer id) {

		Optional<Question> question = qstnRepo.findById(id);
		return question.map(QuestionMapper::toDto).orElse(null);
	}

}
