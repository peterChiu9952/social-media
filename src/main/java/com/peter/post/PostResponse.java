package com.peter.post;

import java.util.Date;

public record PostResponse(
        Long postId,
        Long userId,
        String content,
        Date createdAt,
        String username) {
}
