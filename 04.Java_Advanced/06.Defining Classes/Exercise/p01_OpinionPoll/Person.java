package DefiningClasses_06.Exercise.p01_OpinionPoll;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age;
    }
}
