package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public enum Corps {
    AIR_FORCES("Airforces"), MARINES("Marines");

    private String basicName;

    Corps(String basicName) {
        this.basicName = basicName;
    }

    public String getBasicName() {
        return basicName;
    }
}
