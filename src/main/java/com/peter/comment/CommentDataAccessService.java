package com.peter.comment;

import com.peter.post.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDataAccessService implements CommentDao {

    private final CommentRepository commentRepository;

    public CommentDataAccessService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> selectAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> selectCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostPostId(postId);
    }

    @Override
    public void deleteCommentsByPostId(Long postId) {
        commentRepository.deleteByPostPostId(postId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentRepository.save(comment);
    }
}
