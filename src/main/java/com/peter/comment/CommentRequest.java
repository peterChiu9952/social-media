package com.peter.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(@NotNull Long postId, @NotBlank String content) {
}
