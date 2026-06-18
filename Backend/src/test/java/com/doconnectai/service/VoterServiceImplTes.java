package com.doconnectai.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.doconnectai.dto.VoteDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.User;
import com.doconnectai.entity.Vote;
import com.doconnectai.entity.VoteType;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.VoteMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.repository.VoteRepo;

class VoterServiceImplTest {

    @InjectMocks
    private VoterServiceImpl voterService;

    @Mock
    private VoteRepo voterRepo;

    @Mock
    private UserRepo uRepo;

    @Mock
    private AnswerRepo ansRepo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("test@example.com");

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);

        SecurityContextHolder.setContext(context);
    }

    // -------------------- ADD VOTE -------------------- //

    @Test
    void testAddVote_NewUpvote() {
        VoteDto dto = new VoteDto();
        dto.setAnswerId(1);
        dto.setType(VoteType.UPVOTE);

        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);
        answer.setVoteCount(0);

        Vote vote = new Vote();

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.empty());
        when(voterRepo.save(any(Vote.class))).thenReturn(vote);

        try (MockedStatic<VoteMapper> mapper = mockStatic(VoteMapper.class)) {
            mapper.when(() -> VoteMapper.toEntity(dto, user, answer)).thenReturn(vote);
            mapper.when(() -> VoteMapper.toDto(vote)).thenReturn(dto);

            VoteDto result = voterService.addVote(dto);

            assertEquals(1, answer.getVoteCount());
            verify(ansRepo).save(answer);
            verify(voterRepo).save(vote);
            assertNotNull(result);
        }
    }

    @Test
    void testAddVote_NewDownvote() {
        VoteDto dto = new VoteDto();
        dto.setAnswerId(1);
        dto.setType(VoteType.DOWNVOTE);

        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);
        answer.setVoteCount(0);

        Vote vote = new Vote();

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.empty());
        when(voterRepo.save(any(Vote.class))).thenReturn(vote);

        try (MockedStatic<VoteMapper> mapper = mockStatic(VoteMapper.class)) {
            mapper.when(() -> VoteMapper.toEntity(dto, user, answer)).thenReturn(vote);
            mapper.when(() -> VoteMapper.toDto(vote)).thenReturn(dto);

            voterService.addVote(dto);

            assertEquals(-1, answer.getVoteCount());
        }
    }

    @Test
    void testAddVote_SameVote_NoChange() {
        VoteDto dto = new VoteDto();
        dto.setAnswerId(1);
        dto.setType(VoteType.UPVOTE);

        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);
        answer.setVoteCount(5);

        Vote existing = new Vote();
        existing.setType(VoteType.UPVOTE);

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.of(existing));

        try (MockedStatic<VoteMapper> mapper = mockStatic(VoteMapper.class)) {
            mapper.when(() -> VoteMapper.toDto(existing)).thenReturn(dto);

            VoteDto result = voterService.addVote(dto);

            assertEquals(5, answer.getVoteCount());
            verify(ansRepo, never()).save(any());
            assertNotNull(result);
        }
    }

    @Test
    void testAddVote_ChangeVote_UpvoteToDownvote() {
        VoteDto dto = new VoteDto();
        dto.setAnswerId(1);
        dto.setType(VoteType.DOWNVOTE);

        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);
        answer.setVoteCount(10);

        Vote existing = new Vote();
        existing.setType(VoteType.UPVOTE);

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.of(existing));
        when(voterRepo.save(existing)).thenReturn(existing);

        try (MockedStatic<VoteMapper> mapper = mockStatic(VoteMapper.class)) {
            mapper.when(() -> VoteMapper.toDto(existing)).thenReturn(dto);

            voterService.addVote(dto);

            assertEquals(8, answer.getVoteCount()); // -2
            verify(ansRepo).save(answer);
        }
    }

    @Test
    void testAddVote_AnswerNotFound() {
        VoteDto dto = new VoteDto();
        dto.setAnswerId(1);

        when(uRepo.findByEmail("test@example.com")).thenReturn(new User());
        when(ansRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            voterService.addVote(dto);
        });
    }

    // -------------------- REMOVE VOTE -------------------- //

    @Test
    void testRemoveVote_Upvote() {
        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);
        answer.setVoteCount(5);

        Vote vote = new Vote();
        vote.setType(VoteType.UPVOTE);

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.of(vote));

        voterService.removeVote(1);

        assertEquals(4, answer.getVoteCount());
        verify(voterRepo).delete(vote);
    }

    @Test
    void testRemoveVote_NotFound() {
        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));
        when(voterRepo.findByUser_IdAndAnswer_Id(1, 1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            voterService.removeVote(1);
        });
    }

    // -------------------- GET VOTES -------------------- //

    @Test
    void testGetVotesByAnswerId() {
        Vote vote = new Vote();

        when(voterRepo.findByAnswer_Id(1)).thenReturn(List.of(vote));

        try (MockedStatic<VoteMapper> mapper = mockStatic(VoteMapper.class)) {
            mapper.when(() -> VoteMapper.toDto(vote)).thenReturn(new VoteDto());

            List<VoteDto> result = voterService.getVotesByAnswerId(1);

            assertEquals(1, result.size());
        }
    }
}