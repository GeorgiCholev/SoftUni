package DefiningClasses_06.Exercise.p07_Google;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final String name;
    private Car car;
    private Company company;
    private final List<PersonInformation> parents;
    private final List<PersonInformation> children;
    private final List<PersonInformation> pokemons;

    public Person(String name) {
        this.name = name;
        parents = new ArrayList<>();
        children = new ArrayList<>();
        pokemons = new ArrayList<>();
    }

    public void setCar(String model, int speed) {
        car = new Car(model, speed);
    }

    public void setCompany(String name, String department, double salary) {
        company = new Company(name, department, salary);
    }

    public void addPokemon(String name, String type) {
        add(new Pokemon(name, type), pokemons);
    }

    public void addChild(String name, String birthday) {
        add(new Human(name, birthday), children);
    }

    public void addParent(String name, String birthday) {
        add(new Human(name, birthday), parents);
    }

    private void add(PersonInformation p, List<PersonInformation> list) {
        list.add(p);
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder(name + System.lineSeparator());

        appendIfPresent("Company:", company, info);
        appendIfPresent("Car:", car, info);
        appendCollection("Pokemon:", pokemons, info);
        appendCollection("Parents:", parents, info);
        appendCollection("Children:", children, info);

        return info.toString().trim();
    }

    private void appendCollection(String information, List<PersonInformation> list, StringBuilder info) {
        info.append(information).append(System.lineSeparator());
        for (PersonInformation personInfo : list) {
            info.append(personInfo.getInfo()).append(System.lineSeparator());
        }
    }

    private void appendIfPresent(String information, PersonInformation personInfo, StringBuilder info){
        info.append(information).append(System.lineSeparator());
        if (personInfo != null) {
            info.append(personInfo.getInfo()).append(System.lineSeparator());
        }
    }


    private class Car implements PersonInformation {
        private final String model;
        private final int speed;

        private Car(String mode, int speed) {
            this.model = mode;
            this.speed = speed;
        }

        @Override
        public String getInfo() {
            return model + " " + speed;
        }
    }

    private class Human implements PersonInformation {
        private final String name;
        private final String birthday;

        private Human(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        @Override
        public String getInfo() {
            return name + " " + birthday;
        }
    }

    private class Pokemon implements PersonInformation {
        private final String name;
        private final String type;

        private Pokemon(String name, String type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String getInfo() {
            return name + " " + type;
        }
    }

    private class Company implements PersonInformation {
        private final String name;
        private final String department;
        private final double salary;

        private Company(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        @Override
        public String getInfo() {
            return name + " " + department + " " + String.format("%.2f", salary);
        }
    }

    private interface PersonInformation {
        String getInfo();
    }
}
