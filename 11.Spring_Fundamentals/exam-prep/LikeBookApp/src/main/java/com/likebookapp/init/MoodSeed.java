package com.likebookapp.init;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodName;
import com.likebookapp.repository.MoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class MoodSeed implements CommandLineRunner {

    private final MoodRepository moodRepository;

    public MoodSeed(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (moodRepository.count() != 0) {
            return;
        }

        moodRepository.saveAll(
                Arrays.stream(MoodName.values())
                        .map(Mood::new)
                        .collect(Collectors.toList())
        );
    }
}
