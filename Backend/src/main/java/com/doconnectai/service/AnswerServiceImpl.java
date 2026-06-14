package com.doconnectai.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnectai.dto.AnswerDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.AnswerMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerServiceImpl implements IAnswerService {

	@Autowired
	private AnswerRepo answrRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private QuestionRepo qstnRepo;

	@Override
	public AnswerDto addAnswer(AnswerDto dto) {
		
		log.info("Adding answer to question ID: {}", dto.getQuestionId());

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepo.findByEmail(email);

		if (user == null) {
			log.warn("Authenticated user not found with email: {}", email);
			throw new ResourceNotFoundException("User not found");
		}

		Question question = qstnRepo.findById(dto.getQuestionId()).orElseThrow(
				() -> new ResourceNotFoundException("Question not found with id : " + dto.getQuestionId()));

		Answer ans = AnswerMapper.toEntity(dto, user, question);

		Answer saved = answrRepo.save(ans);
		
		log.info("Answer added successfully. ID: {}", saved.getId());

		return AnswerMapper.toDto(saved);
	}

	@Override
	public List<AnswerDto> getAnswerByQuestionId(Integer questionId) {

		List<Answer> ans = answrRepo.findByQuestion_Id(questionId);

		return ans.stream().map(AnswerMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public AnswerDto updateAnswer(Integer id, AnswerDto dto) {

		log.info("Updating answer with ID: {}", id);
		
		Answer answer = answrRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Answer not found with id : " + id));

		answer.setContent(dto.getContent());

		Answer updated = answrRepo.save(answer);
		
		log.info("Answer updated successfully. ID: {}", updated.getId());

		return AnswerMapper.toDto(updated);
	}

	@Override
	public void deleteAnswer(Integer id) {
		
		log.info("Deleting answer with ID: {}", id);

		if (!answrRepo.existsById(id)) {
			log.warn("Answer not found with ID: {}", id);
			throw new ResourceNotFoundException("Answer not found with id : " + id);
		}
		
		log.info("Answer deleted successfully. ID: {}", id);

		answrRepo.deleteById(id);
	}

}
