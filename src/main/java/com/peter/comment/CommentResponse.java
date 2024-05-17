package com.peter.comment;

import java.util.Date;

public record CommentResponse(
        Long commentId,
        Long postId,
        Long userId,
        String username,
        String content,
        Date createdAt
) {
}
