package com.example.bookshopsystem;

import com.example.bookshopsystem.problems.ProblemSolver;
import com.example.bookshopsystem.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ProblemSolver problemSolver;

    private final SeedService seedService;

    @Autowired
    public ConsoleRunner(ProblemSolver problemSolver, SeedService seedService) {
        this.problemSolver = problemSolver;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
//        fill Database:
//        seedService.seedDatabase();

        System.out.print("Pick a problem [1 - 13]:   ");
        problemSolver.navigateToProblem(new Scanner(System.in).nextInt());

    }
}
