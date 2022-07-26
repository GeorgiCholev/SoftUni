package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public class Mission {

    private String codeName;
    private MissionStates missionState;

    public Mission(String codeName, String missionState) {
        this.codeName = codeName;
        setMissionState(missionState);
    }

    private void setMissionState(String missionState) {
        if ("inProgress".equals(missionState)) {
            this.missionState = MissionStates.IN_PROGRESS;
        } else if ("finished".equals(missionState)) {
            this.missionState = MissionStates.FINISHED;
        } else {
            this.missionState = null;
        }
    }

    public String getCodeName() {
        return codeName;
    }

    public MissionStates getMissionState() {
        return missionState;
    }

    @Override
    public String toString() {
        return "  Code Name: " + codeName + " State: " + getMissionState().getBasicState();
    }
}
