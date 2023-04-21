package rpg_lab_tests;

import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

import static org.junit.Assert.assertEquals;

public class AxeTest {

    private static final int AXE_ATTACK = 15;

    private Axe brokenAxe;
    private Axe workingAxe;
    private Dummy target;

    @Before
    public void setUp() {
        brokenAxe = new Axe(AXE_ATTACK, 0);
        workingAxe = new Axe(AXE_ATTACK, 17);
        target = new Dummy(12, 45);
    }

    @Test
    public void axeLoses1Durability_AfterAttack() {

        int expectedNewDurability = workingAxe.getDurabilityPoints() - 1;

        workingAxe.attack(target);

        assertEquals(expectedNewDurability, workingAxe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void attackThrowsException_IfAxeDurability_IsLessThan1() {

        brokenAxe.attack(target);
    }

}