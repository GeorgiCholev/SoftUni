package com.likebookapp.service;

import com.likebookapp.model.dto.PostViewDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public HomeService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Set<PostViewDTO> getAllPostsForUser(Long userId, String username) {
        Set<Post> posts = postRepository.findAllByUserId(userId);
        return posts.stream()
                .map(p ->
                        new PostViewDTO(p.getId(), username, p.getMood().getMoodName(),
                                p.getUserLikes().size(), p.getContent()))
                .collect(Collectors.toSet());
    }

    @Transactional
    public Set<PostViewDTO> getAllPostsNotFor(Long userId, String username) {
        Set<Post> postsForUser = postRepository.findAllByUserId(userId);
        Set<Post> allPosts = new HashSet<>(postRepository.findAll());
        allPosts.removeAll(postsForUser);

        return allPosts.stream()
                .map(p -> new PostViewDTO(p.getId(), p.getUser().getUsername(), p.getMood().getMoodName(),
                        p.getUserLikes().size(), p.getContent()))
                .collect(Collectors.toSet());
    }
}
