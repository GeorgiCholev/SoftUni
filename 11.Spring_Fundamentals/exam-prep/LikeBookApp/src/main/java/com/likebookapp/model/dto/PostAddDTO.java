package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostAddDTO {

    @NotBlank
    @Size(min = 2, max = 150)
    private String content;

    @NotNull
    private MoodName mood;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodName getMood() {
        return mood;
    }

    public void setMood(MoodName mood) {
        this.mood = mood;
    }
}
