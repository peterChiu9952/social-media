package com.peter.post;

import java.util.Date;

public record PostResponse(
        Long postId,
        String username,
        String content,
        Date createdAt) {
}
