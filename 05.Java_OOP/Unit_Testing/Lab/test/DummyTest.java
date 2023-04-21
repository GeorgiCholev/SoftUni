package rpg_lab_tests;

import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;

import static org.junit.Assert.*;

public class DummyTest {

    private static final int NO_HEALTH = 0;
    private static final int SOME_HEALTH = 10;
    private static final int EXPERIENCE = 30;

    private static final int ATTACK_POINTS = 1;

    private Dummy deadDummy;
    private Dummy aliveDummy;

    @Before
     public void setUp() {
        deadDummy = new Dummy(NO_HEALTH, EXPERIENCE);
        aliveDummy = new Dummy(SOME_HEALTH, EXPERIENCE);
    }

    @Test
    public void isDeadReturnsTrue_ForHealthLessThan_1() {
        assertTrue(deadDummy.isDead());
    }

    @Test
    public void isDeadReturnsFalse_ForHealthMoreThan_0() {
        assertFalse(aliveDummy.isDead());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAttackDeadDummy() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void aliveDummyReducesHealth_EqualToAttack() {
        int expectedHealth = aliveDummy.getHealth() - ATTACK_POINTS;

        aliveDummy.takeAttack(ATTACK_POINTS);

        assertEquals(expectedHealth, aliveDummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummy_CannotGiveExperience() {
        aliveDummy.giveExperience();
    }

    @Test
    public void deadDummyReturnsXP_EqualToItsXP_Field() {

       assertEquals(EXPERIENCE, deadDummy.giveExperience());
    }
}
