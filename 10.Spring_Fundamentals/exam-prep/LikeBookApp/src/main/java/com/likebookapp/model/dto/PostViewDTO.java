package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodName;

public class PostViewDTO {

    private final Long postId;
    private final String username;

    private final String mood;

    private final Integer numberOfLikes;

    private final String content;

    public PostViewDTO(Long postId, String username, MoodName moodName, Integer numberOfLikes, String content) {
        this.postId = postId;
        this.username = username;
        this.mood = moodName.getLabel();
        this.numberOfLikes = numberOfLikes;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getMood() {
        return mood;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostViewDTO that = (PostViewDTO) o;

        if (!postId.equals(that.postId)) return false;
        if (!username.equals(that.username)) return false;
        if (!mood.equals(that.mood)) return false;
        if (!numberOfLikes.equals(that.numberOfLikes)) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        int result = postId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + mood.hashCode();
        result = 31 * result + numberOfLikes.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}
