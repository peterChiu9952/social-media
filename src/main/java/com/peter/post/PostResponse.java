package com.peter.post;

import java.util.Date;

public record PostResponse(
        Long postId,
        Long userId,
        String username,
        String content,
        Date createdAt) {
}
