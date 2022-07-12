package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

import java.util.Collection;
import java.util.HashSet;

public class CommandoImpl  extends SpecialisedSoldierImpl implements Commando {

    Collection<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        missions = new HashSet<>();
    }


    public void addMission(Mission mission) {
        missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return missions;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(super.toString() + System.lineSeparator());
        buffer.append("Missions:").append(System.lineSeparator());

        for (Mission mission : missions) {
            buffer.append(mission).append(System.lineSeparator());
        }
        return buffer.toString();
    }
}
