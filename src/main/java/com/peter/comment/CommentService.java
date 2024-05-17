package com.peter.comment;

import com.peter.exception.ResourceNotFoundException;
import com.peter.jwt.JWTUtil;
import com.peter.post.Post;
import com.peter.post.PostDao;
import com.peter.user.User;
import com.peter.user.UserDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentDao commentDao;
    private final PostDao postDao;
    private final UserDao userDao;
    private final CommentResponseMapper commentResponseMapper;
    private final JWTUtil jwtUtil;

    public CommentService(CommentDao commentDao, PostDao postDao, UserDao userDao, CommentResponseMapper commentResponseMapper, JWTUtil jwtUtil) {
        this.commentDao = commentDao;
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentResponseMapper = commentResponseMapper;
        this.jwtUtil = jwtUtil;
    }

    public List<CommentResponse> getAllComments() {
        return commentDao.selectAllComments()
                .stream()
                .map(commentResponseMapper)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentDao.selectCommentsByPostId(postId)
                .stream()
                .map(commentResponseMapper)
                .collect(Collectors.toList());
    }

    public void createComment(CommentRequest request, String token) {
        Post post = postDao.selectPostByPostId(request.postId()).orElseThrow(()
                -> new ResourceNotFoundException("post with postId [%s] not found.".formatted(request.postId())));
        Long userId = getUserIdByToken(token);

        commentDao.insertComment(new Comment(
                userId,
                post,
                request.content(),
                new Date()
        ));
    }

    private Long getUserIdByToken(String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        User user = userDao.selectUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user with username [%s] not found.".formatted(username)));
        return user.getUserId();
    }
}
