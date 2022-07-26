package ExamPreparation_10.October_23rd_2021.p03_Hotel;

import java.util.ArrayList;
import java.util.Collection;

public class Hotel {

    private Collection<Person> roster;
    private String name;
    private int capacity;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void add(Person person) {
        if (roster.size() < capacity) {
            roster.add(person);
        }
    }

    public boolean remove(String name) {
        return roster.removeIf(person -> person.getName().equals(name));
    }

    public Person getPerson(String name, String hometown) {
        for (Person person : roster) {
            if (person.getName().equals(name) && person.getHometown().equals(hometown)) {
                return person;
            }
        }
        return null;
    }

    public int getCount() {
        return roster.size();
    }

    public String getStatistics() {

        StringBuilder buffer =
                new StringBuilder("The people in the ExamPreparation_10.October_23rd_2021.hotel " + this.name + " are:" + System.lineSeparator());
        roster.forEach(person -> buffer.append(person).append(System.lineSeparator()));
        return buffer.toString().trim();
    }
}
