package com.doconnectai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public AnswerDto addAnswer(AnswerDto answer) {
		
		Optional <User> userOpt = userRepo.findById(answer.getUserId());
		
		Optional <Question> qstnOpt = qstnRepo.findById(answer.getUserId());
		
		if(userOpt.isEmpty() || qstnOpt.isEmpty()) {
			return null;
		}
		
		User user = userOpt.get();
		Question qstn = qstnOpt.get();
		
		Answer ans = AnswerMapper.toEntity(answer, user, qstn);
		
		Answer saved = answrRepo.save(ans);
		
		return AnswerMapper.toDto(saved);
	}

	@Override
	public List<AnswerDto> getAnswerByQuestionId(int questionId) {
		
		List<Answer> ans = answrRepo.findByQuestionId(questionId);
		
		return ans.stream()
				.map(AnswerMapper::toDto)
				.collect(Collectors.toList());
	}

}
