package com.example.gamestore.sevices.game;

import com.example.gamestore.constants.Commands;
import com.example.gamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.gamestore.constants.Commands.*;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void acquireService(String[] consoleData) {

        String service = consoleData[0];
        final String[] updatedData = Arrays.copyOfRange(consoleData, 1, consoleData.length - 1);

        switch (service) {
            case ADD_GAME -> addGame(updatedData);
            case EDIT_GAME -> editGame(updatedData);
            case DELETE_GAME -> deleteGame(Integer.parseInt(consoleData[1]));
        }
    }

    private void deleteGame(int id) {

    }

    private void editGame(String[] data) {

    }

    private void addGame(String[] data) {

    }
}
