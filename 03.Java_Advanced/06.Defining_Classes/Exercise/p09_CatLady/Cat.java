package DefiningClasses_06.Exercise.p09_CatLady;

public abstract class Cat {

    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected abstract String getInfo();
}
