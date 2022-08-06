package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public class SpyImpl extends SoldierImpl implements Spy{

    private int codeNumber;

    public SpyImpl(int id, String firstName, String lastName, int codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public int getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "Code Number: " + codeNumber;
    }
}
