package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.UserLogin;
import com.example.spotifyplaylistapp.model.dtos.UserRegister;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.sessionModel.CurrentUser;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean register(UserRegister userRegister) {

        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            return false;
        }

        if (userRepository.findByUsernameOrEmail(userRegister.getUsername(), userRegister.getEmail()).isPresent()) {
            return false;
        }

        userRepository.save(new User(userRegister));
        return true;
    }

    public boolean logIn(UserLogin userLogin) {

        Optional<User> optUser = userRepository.findByUsername(userLogin.getUsername());

        if (optUser.isEmpty()) {
            return false;
        }

        User user = optUser.get();

        if (!user.getPassword().equals(userLogin.getPassword())) {
            return false;
        }

        currentUser.becomes(user);
        return true;
    }

    public void logOut() {
        currentUser.clear();
    }


    public List<Song> getSongsFor(Long id) {
        return userRepository.findById(id).get().getSongs();
    }

    public void addSongTo(Long id, Song song) {
        User user = userRepository.findById(id).get();
        List<Song> songs = user.getSongs();
        if (songs.stream().noneMatch(s -> s.getId().equals(song.getId()))) {
            songs.add(song);
        }

        userRepository.save(user);
    }

//    @Transactional
    public void removeAllSongs(Long id) {
        User user = userRepository.findById(id).get();
        user.getSongs().clear();
    }
}
