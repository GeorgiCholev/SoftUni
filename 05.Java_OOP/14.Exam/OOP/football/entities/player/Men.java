package football.entities.player;

public class Men extends BasePlayer {

    private static final double KILOGRAMS = 85.5;
    private static final int STRENGTH_STIMULATION = 145;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        super.setKg(KILOGRAMS);
    }

    @Override
    public void stimulation() {
        super.strengthIncreaseAfterStimulation(STRENGTH_STIMULATION);
    }
}
