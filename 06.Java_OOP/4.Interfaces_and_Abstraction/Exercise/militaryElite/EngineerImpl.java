package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

import java.util.Collection;
import java.util.HashSet;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    Collection<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        repairs = new HashSet<>();
    }


    public void addRepair(Repair repair) {
        repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return repairs;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(super.toString() + System.lineSeparator());
                buffer.append("Repairs:").append(System.lineSeparator());
        for (Repair repair : repairs) {
            buffer.append(repair).append(System.lineSeparator());
        }
        return buffer.toString();
    }
}
