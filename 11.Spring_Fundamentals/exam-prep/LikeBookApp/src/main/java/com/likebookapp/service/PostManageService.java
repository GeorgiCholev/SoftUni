package com.likebookapp.service;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostManageService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final MoodRepository moodRepository;

    public PostManageService(UserRepository userRepository, PostRepository postRepository, MoodRepository moodRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
    }

    public void addPostForUser(PostAddDTO dto, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        Mood mood = moodRepository.findByMoodName(dto.getMood()).orElse(null);

        if (user == null || mood == null) {
            return;
        }

        postRepository.save(new Post(dto, mood, user));
    }

    public void removePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void likePost(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);

        if (user == null || post == null) return;

        if (post.getUserLikes().stream().noneMatch(u -> u.getId().equals(user.getId()))) {
            post.getUserLikes().add(user);
        }
    }
}
