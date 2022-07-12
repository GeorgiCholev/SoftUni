package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {

    private Corps soldierCorp;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary);
        setSoldierCorp(corp);
    }

    @Override
    public Corps getSoldierCorp() {
        return soldierCorp;
    }

    private void setSoldierCorp(String corp) {
        if ("Airforces".equals(corp)) {
            this.soldierCorp = Corps.AIR_FORCES;
        } else if ("Marines".equals(corp)) {
            this.soldierCorp = Corps.MARINES;
        } else {
            this.soldierCorp = null;
        }
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "Corps: " + getSoldierCorp().getBasicName();
    }
}
