package com.doconnectai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.QuestionMapper;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private QuestionRepo qstnRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public QuestionDto addQuestion(QuestionDto question) {

		log.info("Creating new question: {}", question.getTitle());

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepo.findByEmail(email);

		if (user == null) {

			log.error("User not found with email: {}", email);
			throw new ResourceNotFoundException("User not found");
		}

		Question qstn = QuestionMapper.toEntity(question, user);

		Question saved = qstnRepo.save(qstn);

		log.info("Question created successfully with id {}", saved.getId());

		return QuestionMapper.toDto(saved);
	}

	@Override
	public List<QuestionDto> getAllQuestion() {


		List<Question> questions = qstnRepo.findAll();


		return questions.stream().map(QuestionMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public QuestionDto getQuestionById(Integer id) {

		log.info("Fetching question with id {}", id);

		Question question = qstnRepo.findById(id).orElseThrow(() -> {
			log.error("Question not found with id {}", id);
			return new ResourceNotFoundException("Question not found with id : " + id);
		});

		log.info("Question fetched successfully with id {}", id);

		return QuestionMapper.toDto(question);
	}

	@Override
	public QuestionDto updateQuestion(Integer id, QuestionDto dto) {

		log.info("Updating question with id {}", id);

		Question question = qstnRepo.findById(id).orElseThrow(() -> {
			log.error("Question not found with id {}", id);
			return new ResourceNotFoundException("Question not found with id : " + id);
		});

		question.setTitle(dto.getTitle());
		question.setDescription(dto.getDescription());

		Question updated = qstnRepo.save(question);
		
		log.info("Updated question with id {}", id);

		return QuestionMapper.toDto(updated);
	}

	@Override
	public void deleteQuestion(Integer id) {
		
		log.info("Deleting question with id {}", id);

		if (!qstnRepo.existsById(id)) {
			log.error("Question not found with id {}", id);
			throw new ResourceNotFoundException("Question not found with id : " + id);
		}

		log.info("Question deleted successfully with id {}", id);

		qstnRepo.deleteById(id);
	}

}
