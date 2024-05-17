package com.peter.comment;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CommentResponseMapper implements Function<Comment, CommentResponse> {

    @Override
    public CommentResponse apply(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getPost().getPostId(),
                comment.getUser().getUserId(),
                comment.getUser().getUsername(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
