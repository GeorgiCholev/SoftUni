package rpg_lab_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.Target;
import rpg_lab.Weapon;

public class HeroTest {

    private Hero hero;
    private Weapon mockedWeapon;
    private Target mockedTarget;

    @Before
    public void setUp() {
        mockedWeapon = Mockito.mock(Weapon.class);
        mockedTarget = Mockito.mock(Target.class);
        hero = new Hero("Lotus", mockedWeapon);
    }

    @Test
    public void killingTargetGivesXP() {
        Assert.assertEquals(0, hero.getExperience());

        Mockito.when(mockedTarget.isDead()).thenReturn(true);
        Mockito.when(mockedTarget.giveExperience()).thenReturn(200);

        hero.attack(mockedTarget);

        Assert.assertEquals(200, hero.getExperience());
    }

}
