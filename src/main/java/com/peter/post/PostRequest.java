package com.peter.post;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(@NotBlank String content) {
}
