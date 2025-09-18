package com.interview.notes.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class NoteDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title cannot be longer then 50 characters")
    private String title;

    @Size(max = 255, message = "Content cannot by longer then 255 characters")
    private String content;
    private LocalDateTime createdAt;

    @Positive(message = "Id must be not null or positive number")
    private Long authorId;

    public NoteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
