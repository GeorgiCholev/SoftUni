package _4_Abstraction_and_Interfaces.Exercise.militaryElite;

import java.util.Collection;
import java.util.TreeSet;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {

    private Collection<PrivateImpl> underCommand;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        underCommand = new TreeSet<>();
    }

    public Collection<PrivateImpl> getUnderCommand() {
        return underCommand;
    }

    public void addPrivate(Private priv) {

        PrivateImpl newGuy = (PrivateImpl) priv;
        underCommand.add(newGuy);

    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(super.toString() + System.lineSeparator());
        buffer.append("Privates:").append(System.lineSeparator());

        for (PrivateImpl aPrivate : underCommand) {
            buffer.append(aPrivate).append(System.lineSeparator());
        }

        return buffer.toString();
    }
}
