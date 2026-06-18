package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
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

import com.doconnectai.dto.AnswerDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Question;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.AnswerMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.QuestionRepo;
import com.doconnectai.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private AnswerRepo answerRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private QuestionRepo qstnRepo;

    @InjectMocks
    private AnswerServiceImpl answerService;

    private User user;
    private Question question;
    private Answer answer;
    private AnswerDto answerDto;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1);
        user.setName("Madhu");
        user.setEmail("madhu@gmail.com");

        question = new Question();
        question.setId(1);
        question.setTitle("JWT");

        answer = new Answer();
        answer.setId(1);
        answer.setContent("JWT uses token.");
        answer.setQuestion(question);
        answer.setUser(user);
        answer.setAccepted(false);

        answerDto = new AnswerDto();
        answerDto.setId(1);
        answerDto.setContent("JWT uses token.");
        answerDto.setQuestionId(1);
    }

    @Test
    void testAddAnswer() {

        SecurityContext securityContext =
                mock(SecurityContext.class);

        Authentication authentication =
                mock(Authentication.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        when(authentication.getName())
                .thenReturn("madhu@gmail.com");

        SecurityContextHolder.setContext(securityContext);

        when(userRepo.findByEmail("madhu@gmail.com"))
                .thenReturn(user);

        when(qstnRepo.findById(1))
                .thenReturn(Optional.of(question));

        when(answerRepo.save(any(Answer.class)))
                .thenReturn(answer);

        try (MockedStatic<AnswerMapper> mapper =
                     mockStatic(AnswerMapper.class)) {

            mapper.when(() ->
                    AnswerMapper.toEntity(
                            answerDto,
                            user,
                            question))
                    .thenReturn(answer);

            mapper.when(() ->
                    AnswerMapper.toDto(answer))
                    .thenReturn(answerDto);

            AnswerDto result =
                    answerService.addAnswer(answerDto);

            assertNotNull(result);
            assertEquals(
                    "JWT uses token.",
                    result.getContent());

            verify(answerRepo)
                    .save(answer);
        }
    }

    @Test
    void testAddAnswerUserNotFound() {

        SecurityContext securityContext =
                mock(SecurityContext.class);

        Authentication authentication =
                mock(Authentication.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        when(authentication.getName())
                .thenReturn("madhu@gmail.com");

        SecurityContextHolder.setContext(securityContext);

        when(userRepo.findByEmail("madhu@gmail.com"))
                .thenReturn(null);

        assertThrows(
                ResourceNotFoundException.class,
                () ->
                        answerService.addAnswer(
                                answerDto));
    }

    @Test
    void testGetAnswerByQuestionId() {

        when(answerRepo.findByQuestion_Id(1))
                .thenReturn(
                        Arrays.asList(answer));

        try (MockedStatic<AnswerMapper> mapper =
                     mockStatic(AnswerMapper.class)) {

            mapper.when(() ->
                    AnswerMapper.toDto(answer))
                    .thenReturn(answerDto);

            List<AnswerDto> result =
                    answerService
                            .getAnswerByQuestionId(1);

            assertEquals(1,
                    result.size());
        }
    }

    @Test
    void testUpdateAnswer() {

        when(answerRepo.findById(1))
                .thenReturn(
                        Optional.of(answer));

        when(answerRepo.save(answer))
                .thenReturn(answer);

        try (MockedStatic<AnswerMapper> mapper =
                     mockStatic(AnswerMapper.class)) {

            mapper.when(() ->
                    AnswerMapper.toDto(answer))
                    .thenReturn(answerDto);

            AnswerDto result =
                    answerService
                            .updateAnswer(
                                    1,
                                    answerDto);

            assertNotNull(result);

            verify(answerRepo)
                    .save(answer);
        }
    }

    @Test
    void testUpdateAnswerNotFound() {

        when(answerRepo.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () ->
                        answerService
                                .updateAnswer(
                                        1,
                                        answerDto));
    }

    @Test
    void testDeleteAnswer() {

        when(answerRepo.existsById(1))
                .thenReturn(true);

        answerService.deleteAnswer(1);

        verify(answerRepo)
                .deleteById(1);
    }

    @Test
    void testDeleteAnswerNotFound() {

        when(answerRepo.existsById(1))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () ->
                        answerService
                                .deleteAnswer(1));
    }

    @Test
    void testAcceptAnswer() {

        question.setUser(user);
        answer.setQuestion(question);

        when(userRepo.findOptionalByEmail(
                "madhu@gmail.com"))
                .thenReturn(Optional.of(user));

        when(answerRepo.findById(1))
                .thenReturn(Optional.of(answer));

        when(answerRepo.findByQuestion_Id(1))
                .thenReturn(
                        Arrays.asList(answer));

        SecurityContext securityContext =
                mock(SecurityContext.class);

        Authentication authentication =
                mock(Authentication.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        when(authentication.getName())
                .thenReturn("madhu@gmail.com");

        SecurityContextHolder.setContext(
                securityContext);

        try (MockedStatic<AnswerMapper> mapper =
                     mockStatic(AnswerMapper.class)) {

            mapper.when(() ->
                    AnswerMapper.toDto(answer))
                    .thenReturn(answerDto);

            AnswerDto result =
                    answerService.acceptAnswer(1);

            assertTrue(answer.isAccepted());
            assertNotNull(result);

            verify(answerRepo)
                    .saveAll(anyList());
        }
    }

    @Test
    void testAcceptAnswerNotQuestionOwner() {

        User anotherUser = new User();
        anotherUser.setId(2);

        question.setUser(anotherUser);
        answer.setQuestion(question);

        when(userRepo.findOptionalByEmail(
                "madhu@gmail.com"))
                .thenReturn(Optional.of(user));

        when(answerRepo.findById(1))
                .thenReturn(Optional.of(answer));

        SecurityContext securityContext =
                mock(SecurityContext.class);

        Authentication authentication =
                mock(Authentication.class);

        when(securityContext.getAuthentication())
                .thenReturn(authentication);

        when(authentication.getName())
                .thenReturn("madhu@gmail.com");

        SecurityContextHolder.setContext(
                securityContext);

        assertThrows(
                RuntimeException.class,
                () ->
                        answerService
                                .acceptAnswer(1));
    }

    @Test
    void testGetAllAnswers() {

        when(answerRepo.findAll())
                .thenReturn(
                        Arrays.asList(answer));

        try (MockedStatic<AnswerMapper> mapper =
                     mockStatic(AnswerMapper.class)) {

            mapper.when(() ->
                    AnswerMapper.toDto(answer))
                    .thenReturn(answerDto);

            List<AnswerDto> result =
                    answerService.getAllAnswers();

            assertEquals(
                    1,
                    result.size());
        }
    }
}