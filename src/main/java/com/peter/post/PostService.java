package com.peter.post;

import com.peter.exception.RequestValidationException;
import com.peter.exception.ResourceNotFoundException;
import com.peter.jwt.JWTUtil;
import com.peter.user.User;
import com.peter.user.UserDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private final PostDao postDao;
    private final UserDao userDao;
    private final JWTUtil jwtUtil;

    public PostService(PostDao postDao, UserDao userDao, JWTUtil jwtUtil) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.jwtUtil = jwtUtil;
    }

    public List<Post> getAllPosts() {
        return postDao.selectAllPosts();
    }

    public Post getPostByPostId(Long postId) {
        return postDao.selectPostByPostId(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post with postId [%s] not found.".formatted(postId)));
    }

    public void createPost(PostRequest request, String token) {
        User user = getUserByToken(token);

        postDao.insertPost(new Post(
                user,
                request.content(),
                new Date()
        ));
    }

    public void deletePost(Long postId, String token) {
        Post post = postDao.selectPostByPostId(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post with postId [%s] not found.".formatted(postId)));

        User user = getUserByToken(token);
        if (!Objects.equals(post.getUser().getUserId(), user.getUserId())) {
            throw new RequestValidationException("You do not have authorization to perform this action.");
        }

        postDao.deletePost(postId);
    }

    public void updatePost(Long postId, PostRequest request, String token) {
        Post post = getPostByPostId(postId);

        User user = getUserByToken(token);
        if (!Objects.equals(post.getUser().getUserId(), user.getUserId())) {
            throw new RequestValidationException("You do not have authorization to perform this action.");
        }

        boolean changes = false;

        if (request.content() != null && !request.content().equals(post.getContent())) {
            post.setContent(request.content());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        postDao.updatePost(post);
    }

    private User getUserByToken(String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        return userDao.selectUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user with username [%s] not found.".formatted(username)));
    }
}
