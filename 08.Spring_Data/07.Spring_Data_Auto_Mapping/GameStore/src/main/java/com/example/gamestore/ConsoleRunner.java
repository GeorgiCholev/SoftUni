package com.example.gamestore;

import com.example.gamestore.sevices.game.GameService;
import com.example.gamestore.sevices.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Outputs.SERVICE_NOT_SUPPORTED_FORMAT;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final Scanner scanner;

    private final UserService userService;

    private final GameService gameService;

    @Autowired
    public ConsoleRunner(Scanner scanner, UserService userService, GameService gameService) {
        this.scanner = scanner;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {

        String input;
        while (!(input = scanner.nextLine()).equals("End")) {
            String[] consoleData = input.split("\\|");

            String requiredService = consoleData[0];

            try {
                switch (requiredService) {
                    case REGISTER_USER, LOG_IN_USER, LOG_OUT_USER -> userService.acquireService(consoleData);
                    case ADD_GAME, EDIT_GAME, ALL_GAMES, DETAILS_GAME, OWNED_GAMES ->
                            gameService.acquireService(consoleData);
                    default -> throw new IllegalAccessException(
                            String.format(SERVICE_NOT_SUPPORTED_FORMAT, requiredService)
                    );
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
