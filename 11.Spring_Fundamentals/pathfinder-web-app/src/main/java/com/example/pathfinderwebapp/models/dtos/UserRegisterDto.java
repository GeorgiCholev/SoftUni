package com.example.pathfinderwebapp.models.dtos;

import com.example.pathfinderwebapp.models.entities.enums.LevelType;
import com.example.pathfinderwebapp.validation.passwordsMatch.PasswordsMatch;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@PasswordsMatch
public class UserRegisterDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @Positive
    @Max(99)
    private int age;

    @NotNull
    private LevelType level;

    @NotNull
    @Size(min = 6)
    private String password;

    private String confirmPassword;

    public UserRegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public LevelType getLevel() {
        return level;
    }

    public void setLevel(LevelType level) {
        this.level = level;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void encodePasswords(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
        this.confirmPassword = passwordEncoder.encode(confirmPassword);
    }
}
