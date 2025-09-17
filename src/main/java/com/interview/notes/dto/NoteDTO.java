package com.interview.notes.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class NoteDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @NotNull
    @Min(1)
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
