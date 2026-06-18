package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.QuestionMapper;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

	@Mock
	private QuestionRepo qstnRepo;

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private QuestionServiceImpl questionService;

	private User user;
	private Question question;
	private QuestionDto questionDto;

	@BeforeEach
	void setUp() {

		user = new User();
		user.setId(1);
		user.setEmail("madhu@gmail.com");
		user.setName("Madhu");

		question = new Question();
		question.setId(1);
		question.setTitle("JWT");
		question.setDescription("How JWT works?");
		question.setUser(user);

		questionDto = new QuestionDto();
		questionDto.setTitle("JWT");
		questionDto.setDescription("How JWT works?");
	}

	@Test
	void testAddQuestion() {

		SecurityContext securityContext = mock(SecurityContext.class);

		Authentication authentication = mock(Authentication.class);

		when(securityContext.getAuthentication()).thenReturn(authentication);

		when(authentication.getPrincipal()).thenReturn("madhu@gmail.com");

		SecurityContextHolder.setContext(securityContext);

		when(userRepo.findByEmail("madhu@gmail.com")).thenReturn(user);

		when(qstnRepo.save(any(Question.class))).thenReturn(question);

		try (MockedStatic<QuestionMapper> mapper = mockStatic(QuestionMapper.class)) {

			mapper.when(() -> QuestionMapper.toEntity(questionDto, user)).thenReturn(question);

			mapper.when(() -> QuestionMapper.toDto(question)).thenReturn(questionDto);

			QuestionDto result = questionService.addQuestion(questionDto);

			assertNotNull(result);
			assertEquals("JWT", result.getTitle());

			verify(userRepo).findByEmail("madhu@gmail.com");

			verify(qstnRepo).save(question);
		}
	}

	@Test
	void testGetAllQuestion() {

		when(qstnRepo.findAll()).thenReturn(Arrays.asList(question));

		try (MockedStatic<QuestionMapper> mapper = mockStatic(QuestionMapper.class)) {

			mapper.when(() -> QuestionMapper.toDto(question)).thenReturn(questionDto);

			var result = questionService.getAllQuestion();

			assertEquals(1, result.size());
		}
	}

	@Test
	void testGetQuestionById() {

		when(qstnRepo.findById(1)).thenReturn(Optional.of(question));

		try (MockedStatic<QuestionMapper> mapper = mockStatic(QuestionMapper.class)) {

			mapper.when(() -> QuestionMapper.toDto(question)).thenReturn(questionDto);

			QuestionDto result = questionService.getQuestionById(1);

			assertNotNull(result);
			assertEquals("JWT", result.getTitle());
		}
	}

	@Test
	void testGetQuestionByIdNotFound() {

		when(qstnRepo.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> questionService.getQuestionById(1));
	}

	@Test
	void testUpdateQuestion() {

		when(qstnRepo.findById(1)).thenReturn(Optional.of(question));

		when(qstnRepo.save(any(Question.class))).thenReturn(question);

		try (MockedStatic<QuestionMapper> mapper = mockStatic(QuestionMapper.class)) {

			mapper.when(() -> QuestionMapper.toDto(question)).thenReturn(questionDto);

			QuestionDto result = questionService.updateQuestion(1, questionDto);

			assertNotNull(result);
			assertEquals("JWT", result.getTitle());

			verify(qstnRepo).save(question);
		}
	}

	@Test
	void testDeleteQuestion() {

		when(qstnRepo.existsById(1)).thenReturn(true);

		doNothing().when(qstnRepo).deleteById(1);

		questionService.deleteQuestion(1);

		verify(qstnRepo).deleteById(1);
	}

	@Test
	void testDeleteQuestionNotFound() {

		when(qstnRepo.existsById(1)).thenReturn(false);

		assertThrows(ResourceNotFoundException.class, () -> questionService.deleteQuestion(1));
	}
}