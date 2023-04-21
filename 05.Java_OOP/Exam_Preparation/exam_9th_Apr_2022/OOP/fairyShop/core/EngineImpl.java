package fairyShop.core;

import fairyShop.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddHelper:
                result = addHelper(data);
                break;
            case AddPresent:
                result = addPresent(data);
                break;
            case AddInstrumentToHelper:
                result = addInstrumentToHelper(data);
                break;
            case CraftPresent:
                result = craftPresent(data);
                break;
            case Report:
                result = report();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }
        return result;
    }

    private String addHelper(String[] data) {
        String type = data[0];
        String name = data[1];
        return controller.addHelper(type, name);
    }

    private String addPresent(String[] data) {
        String name = data[0];
        int energy = Integer.parseInt(data[1]);
        return controller.addPresent(name, energy);
    }

    private String addInstrumentToHelper(String[] data) {
        String name = data[0];
        int power = Integer.parseInt(data[1]);
        return controller.addInstrumentToHelper(name, power);
    }

    private String report() {
        return controller.report();
    }

    private String craftPresent(String[] data) {
        String name = data[0];
        return controller.craftPresent(name);
    }
}
