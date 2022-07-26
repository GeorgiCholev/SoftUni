package _4_Abstraction_and_Interfaces.Lab.sayHello;

public class Chinese extends BasePerson {

    public Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Nǐ hǎo";
    }

}
