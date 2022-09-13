package DefiningClasses_06.Exercise.p09_CatLady;

public class Siamese extends Cat {

    private int earSize;

    public Siamese(String name, int earSize) {
        super(name);
        this.earSize = earSize;
    }

    @Override
    protected String getInfo() {
        return "Siamese " + getName() + " " + earSize;
    }

}

