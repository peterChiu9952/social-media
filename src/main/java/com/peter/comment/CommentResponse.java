package com.peter.comment;

import java.util.Date;

public record CommentResponse(
        Long commentId,
        Long userId,
        Long postId,
        String content,
        Date createdAt
) {
}
