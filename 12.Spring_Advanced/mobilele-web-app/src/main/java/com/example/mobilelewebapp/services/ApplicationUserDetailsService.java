package com.example.mobilelewebapp.services;

import com.example.mobilelewebapp.models.entities.Role;
import com.example.mobilelewebapp.models.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = this.userService.getByUsername(username);
        if (entity == null) {
            throw new UsernameNotFoundException("User with name " + username + " not found!");
        }

        Set<Role> userRoles = this.userService.getUserRoles(entity.getId());
        return this.mapToUserDetails(entity, userRoles);
    }

    private UserDetails mapToUserDetails(User entity, Set<Role> userRoles) {
        return new org.springframework.security.core.userdetails.User(
                entity.getUsername(), entity.getPassword(), this.extractAuthorities(userRoles)
        );
    }

    private Collection<GrantedAuthority> extractAuthorities(Set<Role> userRoles) {
        return userRoles.stream()
                .map(this::mapToAuthority)
                .toList();
    }

    private GrantedAuthority mapToAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getRoleType().name());
    }
}
