package com.likebookapp.model.entity;

import com.likebookapp.model.dto.PostAddDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "users_likes_posts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userLikes;

    @ManyToOne(optional = false)
    private Mood mood;

    public Post() {
    }

    public Post(PostAddDTO dto, Mood mood, User user) {
        this.content = dto.getContent();
        this.mood = mood;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public Mood getMood() {
        return mood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
