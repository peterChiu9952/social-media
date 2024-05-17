package com.peter.comment;

import com.peter.post.Post;

import java.util.List;

public interface CommentDao {
    List<Comment> selectAllComments();

    List<Comment> selectCommentsByPostId(Long postId);
    void deleteCommentsByPostId(Long postId);

    void insertComment(Comment comment);
}
