package com.example.campusbridge.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentDto {
    @NotBlank(message = "Author name is required")
    private String authorName;

    @NotBlank(message = "Message is required")
    private String message;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
