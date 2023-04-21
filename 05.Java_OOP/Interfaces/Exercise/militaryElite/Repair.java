package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public class Repair {

    private String partName;
    private int hoursWorked;

    public Repair(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }

    public String getPartName() {
        return partName;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public String toString() {
        return "  Part Name: " + partName + " Hours Worked: " + hoursWorked;
    }
}
