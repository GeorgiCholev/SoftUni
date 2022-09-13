package DefiningClasses_06.Exercise.p09_CatLady;

public class Cymric extends Cat {

    private int furMillMetresLong;

    public Cymric(String name, int furMillMetresLong) {
        super(name);
        this.furMillMetresLong = furMillMetresLong;
    }

    @Override
    protected String getInfo() {
        return "Cymric " + getName() + " " + furMillMetresLong;
    }
}
