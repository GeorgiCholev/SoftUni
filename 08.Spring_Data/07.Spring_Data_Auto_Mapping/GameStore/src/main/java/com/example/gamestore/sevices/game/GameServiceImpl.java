package com.example.gamestore.sevices.game;

import com.example.gamestore.exceptions.InvalidStateException;
import com.example.gamestore.models.Game;
import com.example.gamestore.models.dto.GameDto;
import com.example.gamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Outputs.*;

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
        final String[] updatedData = Arrays.copyOfRange(consoleData, 1, consoleData.length);

        switch (service) {
//          AddGame|title|price|size|trailer|thumbnailURL|description|releaseDate
            case ADD_GAME -> addGame(updatedData);

//          EditGame|id|values
            case EDIT_GAME -> editGame(updatedData);
//
//            case ALL_GAMES -> ;
//            case DETAILS_GAME ->;
//            case OWNED_GAMES ->;
        }
    }

    private void editGame(String[] data) {

        String id = data[0];
        Game gameById;
        try {
            gameById = this.gameRepository
                    .findById(Long.parseLong(id))
                    .orElseThrow(() -> new InvalidStateException(GAME_DOES_NOT_EXIST));
        } catch (InvalidStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        Map<String, String> newValuesForFields = new HashMap<>();
        for (int i = 1; i < data.length; i++) {

            String[] fieldAndValue = data[i].split("=");
            newValuesForFields.put(fieldAndValue[0], fieldAndValue[1]);
        }

        try {
            assignNewFieldValues(gameById, newValuesForFields);
        } catch (InvalidStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        this.gameRepository.save(gameById);

        System.out.printf(SUCCESSFUL_GAME_EDITED_FORMAT, gameById.getTitle());
    }

    private void assignNewFieldValues(Game game, Map<String, String> newValuesForFields)
            throws InvalidStateException {
        for (Map.Entry<String, String> entry : newValuesForFields.entrySet()) {
            try {
                setNewValue(entry, game);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new InvalidStateException(String.format(FIELD_CAN_NOT_BE_MODIFIED, entry.getKey()));
            }
        }
    }

    private void setNewValue(Map.Entry<String, String> entry, Game game)
            throws NoSuchFieldException, IllegalAccessException {

        Field fieldToModify = Game.class.getDeclaredField(entry.getKey());
        Class<?> type = fieldToModify.getType();

        Object valueToAdd = entry.getValue();
        if (type.isAssignableFrom(Double.class)) {

            valueToAdd = Double.parseDouble(entry.getValue());

        } else if (type.isAssignableFrom(LocalDate.class)) {

            int[] dateComponents = Arrays.stream(entry.getValue().split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            valueToAdd = LocalDate.of(dateComponents[2], dateComponents[1], dateComponents[0]);

        } else if (type.isAssignableFrom(BigDecimal.class)) {

            valueToAdd = BigDecimal.valueOf(Double.parseDouble(entry.getValue()));
        }

        fieldToModify.setAccessible(true);
        fieldToModify.set(game, valueToAdd);
    }


    private void addGame(String[] data) {

        String title = data[0];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(data[1]));
        double size = Double.parseDouble(data[2]);
        String trailer = data[3];
        String imageThumbnail = data[4];
        String description = data[5];
        int[] dateComponents =
                Arrays.stream(data[6].split("-"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        LocalDate releaseDate = LocalDate.of(dateComponents[2], dateComponents[1], dateComponents[0]);

        GameDto gameDto;
        try {
            gameDto = new GameDto(title, trailer, imageThumbnail, size, price, description, releaseDate);
        } catch (InvalidStateException e) {
            System.out.println(e.getMessage());
            return;
        }

        Game gameMapped = modelMapper.map(gameDto, Game.class);

        this.gameRepository.save(gameMapped);

        System.out.printf(SUCCESSFUL_GAME_ADDED_FORMAT, gameMapped.getTitle());
    }
}
