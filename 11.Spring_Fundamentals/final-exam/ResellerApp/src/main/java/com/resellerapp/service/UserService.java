package com.resellerapp.service;

import com.resellerapp.config.sessionUser.CurrentUser;
import com.resellerapp.model.dtos.OfferViewDto;
import com.resellerapp.model.dtos.OffersForUser;
import com.resellerapp.model.dtos.UserLoginDto;
import com.resellerapp.model.dtos.UserRegisterDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, CurrentUser currentUser,
                       PasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    public boolean register(UserRegisterDto dto) {
        if (getUserWith(dto.getUsername()) != null) return false;

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) return false;

        encodePasswordFor(dto);
        userRepository.save(new User(dto));
        return true;
    }

    public boolean login(UserLoginDto dto) {
        User user = getUserWith(dto.getUsername());

        if (user == null) return false;
        if (passwordsNotMatch(dto, user)) return false;

        currentUser.login(user);
        return true;
    }

    public void logout() {
        currentUser.logout();
        httpSession.invalidate();
    }

    public void addOfferFor(Long id, Offer offer) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return;

        user.getOffers().add(offer);
    }

    @Transactional
    public OffersForUser getOffersForUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return new OffersForUser(Collections.emptySet(), Collections.emptySet());

        Set<OfferViewDto> userOffers = user.getOffers().stream()
                .map(OfferViewDto::new)
                .collect(Collectors.toSet());

        Set<OfferViewDto> userBoughtOffers = user.getBoughtOffers()
                .stream().map(OfferViewDto::new)
                .collect(Collectors.toSet());

        return new OffersForUser(userOffers, userBoughtOffers);
    }


    public User getUserWith(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    private boolean passwordsNotMatch(UserLoginDto dto, User user) {
        return !passwordEncoder.matches(dto.getPassword(), user.getPassword());
    }

    private void encodePasswordFor(UserRegisterDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    private User getUserWith(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public void buyNow(Long userId, Offer offer) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;

        user.getBoughtOffers().add(offer);
    }
}
