package football.entities.player;

import static football.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player {

    private String name;
    private String nationality;
    private double kg;
    private int strength;

    protected BasePlayer(String name, String nationality, int strength) {
        setName(name);
        setNationality(nationality);
        setInitialStrength(strength);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        validateStringForNullOrEmpty(name, PLAYER_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    private void setNationality(String nationality) {
        validateStringForNullOrEmpty(nationality, PLAYER_NATIONALITY_NULL_OR_EMPTY);
        this.nationality = nationality;
    }

    private void validateStringForNullOrEmpty(String arg, String excMessage) {
        if (arg == null || arg.trim().isEmpty()) {
            throw new NullPointerException(PLAYER_NAME_NULL_OR_EMPTY);
        }
    }

    @Override
    public double getKg() {
        return kg;
    }

    protected void setKg(double kg) {
        this.kg = kg;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    private void setInitialStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    protected void strengthIncreaseAfterStimulation(int strengthIncrease) {
        strength += strengthIncrease;
    }
}
