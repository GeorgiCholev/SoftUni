package football.entities.player;

public class Women extends BasePlayer {

    private static final double KILOGRAMS = 60.0;
    private static final int STRENGTH_STIMULATION = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        super.setKg(KILOGRAMS);
    }

    @Override
    public void stimulation() {
       super.strengthIncreaseAfterStimulation(STRENGTH_STIMULATION);
    }
}
