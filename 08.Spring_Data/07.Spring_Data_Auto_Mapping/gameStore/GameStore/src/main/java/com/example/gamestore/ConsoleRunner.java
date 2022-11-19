package com.example.gamestore;

import com.example.gamestore.sevices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;

    private final UserService userService;

    @Autowired
    public ConsoleRunner(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        String[] consoleData = scanner.nextLine().split("\\|");

        String requiredService = consoleData[0];

        if (requiredService.equals("RegisterUser") ||
                requiredService.equals("LoginUser") ||
                requiredService.equals("Logout")) {

            userService.acquireService(consoleData);

        }
    }
}
