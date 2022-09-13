package DefiningClasses_06.Exercise.p09_CatLady;

public class StreetExtraordinaire extends Cat {

    private int nightMeowDecibels;

    public StreetExtraordinaire(String name, int nightMeowDecibels) {
        super(name);
        this.nightMeowDecibels = nightMeowDecibels;
    }

    @Override
    protected String getInfo() {
        return "StreetExtraordinaire " + getName() + " " + nightMeowDecibels;
    }
}

