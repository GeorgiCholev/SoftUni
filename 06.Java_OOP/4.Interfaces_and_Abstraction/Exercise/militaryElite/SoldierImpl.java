package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public class SoldierImpl implements Soldier, Comparable<SoldierImpl>{

    private int id;
    private String firstName;
    private String lastName;

    public SoldierImpl(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName() + " Id: " + getId();
    }

    @Override
    public int compareTo(SoldierImpl o) {
        return Integer.compare(o.id, this.id);
    }
}
