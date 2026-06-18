package com.doconnectai.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
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

import com.doconnectai.dto.CommentDto;
import com.doconnectai.entity.Answer;
import com.doconnectai.entity.Comment;
import com.doconnectai.entity.User;
import com.doconnectai.exception.ResourceNotFoundException;
import com.doconnectai.mapper.CommentMapper;
import com.doconnectai.repository.AnswerRepo;
import com.doconnectai.repository.CommentRepo;
import com.doconnectai.repository.UserRepo;
import com.doconnectai.service.CommentServiceImpl;

class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepo cmntRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AnswerRepo ansRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock SecurityContext
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("test@example.com");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    // -------------------- ADD COMMENT -------------------- //

    @Test
    void testAddComment_Success() {
        CommentDto dto = new CommentDto();
        dto.setAnswerId(1);

        User user = new User();
        user.setId(1);

        Answer answer = new Answer();
        answer.setId(1);

        Comment comment = new Comment();
        comment.setId(100);

        when(userRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.of(answer));

        // Mock mapping + save
        when(cmntRepo.save(any(Comment.class))).thenReturn(comment);

        try (MockedStatic<CommentMapper> mapper = mockStatic(CommentMapper.class)) {
            mapper.when(() -> CommentMapper.toEntity(dto, user, answer)).thenReturn(comment);
            mapper.when(() -> CommentMapper.toDto(comment)).thenReturn(dto);

            CommentDto result = commentService.addComment(dto);

            assertNotNull(result);
            verify(cmntRepo).save(comment);
        }
    }

    @Test
    void testAddComment_UserNotFound() {
        CommentDto dto = new CommentDto();
        dto.setAnswerId(1);

        when(userRepo.findByEmail("test@example.com")).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> {
            commentService.addComment(dto);
        });
    }

    @Test
    void testAddComment_AnswerNotFound() {
        CommentDto dto = new CommentDto();
        dto.setAnswerId(1);

        User user = new User();

        when(userRepo.findByEmail("test@example.com")).thenReturn(user);
        when(ansRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            commentService.addComment(dto);
        });
    }

    // -------------------- GET COMMENTS -------------------- //

    @Test
    void testGetCommentsByAnswerId() {
        Comment comment = new Comment();

        List<Comment> comments = List.of(comment);

        when(cmntRepo.findByAnswer_Id(1)).thenReturn(comments);

        try (MockedStatic<CommentMapper> mapper = mockStatic(CommentMapper.class)) {
            mapper.when(() -> CommentMapper.toDto(comment)).thenReturn(new CommentDto());

            List<CommentDto> result = commentService.getCommentsByAnswer_Id(1);

            assertEquals(1, result.size());
        }
    }

    // -------------------- DELETE COMMENT -------------------- //

    @Test
    void testDeleteComment_Success() {
        when(cmntRepo.existsById(1)).thenReturn(true);

        commentService.deleteComment(1);

        verify(cmntRepo).deleteById(1);
    }

    @Test
    void testDeleteComment_NotFound() {
        when(cmntRepo.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(1);
        });
    }
}