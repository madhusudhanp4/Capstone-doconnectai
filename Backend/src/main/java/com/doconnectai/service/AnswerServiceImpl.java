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
import com.doconnectai.mapper.AnswerMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

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

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepo.findByEmail(email);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		Question question = qstnRepo.findById(dto.getQuestionId())
				.orElseThrow(() -> new RuntimeException("Question not found"));

		Answer ans = AnswerMapper.toEntity(dto, user, question);

		Answer saved = answrRepo.save(ans);

		return AnswerMapper.toDto(saved);
	}
	

	@Override
	public List<AnswerDto> getAnswerByQuestionId(Integer questionId) {

		List<Answer> ans = answrRepo.findByQuestion_Id(questionId);

		return ans.stream().map(AnswerMapper::toDto).collect(Collectors.toList());
	}

}
