package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public enum MissionStates {
    IN_PROGRESS("inProgress"), FINISHED("finished");

    private String basicState;

    MissionStates(String basicState) {
        this.basicState = basicState;
    }

    public String getBasicState() {
        return basicState;
    }
}
