package _4_Abstraction_and_Interfaces.Lab.sayHello;

public abstract class BasePerson implements Person {

    private String name;

    protected BasePerson(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
