package football;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FootballTeamTests {
    private static final String VALID_NAME = "Rangers";
    private static final String GOALKEEPER_NAME = "Matei";
    private static final String WING_NAME = "Aleksei";
    private static final String STRIKER_NAME = "Andrei";
    private static final int VALID_VACANT_POSITIONS = 3;

    private FootballTeam team;
    private Footballer goalKeeper;
    private Footballer wing;
    private Footballer striker;
    private Footballer anotherStriker;

    @Before
    public void setUp() {
        team = new FootballTeam(VALID_NAME, VALID_VACANT_POSITIONS);
        goalKeeper = new Footballer(GOALKEEPER_NAME);
        wing = new Footballer(WING_NAME);
        striker = new Footballer(STRIKER_NAME);
        anotherStriker = new Footballer("Leo");
    }

    private void addFootballersToTeam() {
        team.addFootballer(goalKeeper);
        team.addFootballer(wing);
        team.addFootballer(striker);
    }

    private void removeFootballersFromTeam() {
        team.removeFootballer(goalKeeper.getName());
        team.removeFootballer(wing.getName());
        team.removeFootballer(striker.getName());
    }

    private String addFootballersAndReturnStatistics() {
        addFootballersToTeam();
        String footballers = GOALKEEPER_NAME + ", " + WING_NAME + ", " + STRIKER_NAME;
        return "The footballer " + footballers + " is in the team " + team.getName() + ".";
    }

    @Test
    public void nameAndVacantPositions_AreSetProperly_WhenValid() {
        assertEquals(VALID_NAME, team.getName());
        assertEquals(VALID_VACANT_POSITIONS, team.getVacantPositions());
    }

    @Test(expected = NullPointerException.class)
    public void nameCannotBeNullOrWhiteSpace() {
        FootballTeam invalidNameTeam = new FootballTeam(null, VALID_VACANT_POSITIONS);
        invalidNameTeam = new FootballTeam("           ", VALID_VACANT_POSITIONS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vacantPositionsCannotBeNegative() {
        FootballTeam invalidVacantPositionsTeam = new FootballTeam(VALID_NAME, -1);
    }

    @Test
    public void addingFootballers_UpToVacantPositionCount_PutsThemOnTheTeam() {
        assertEquals(0, team.getCount());
        addFootballersToTeam();
        assertEquals(3, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingFootballers_PastVacantPositions_Throws() {
        addFootballersToTeam();
        team.addFootballer(anotherStriker);
    }

    @Test
    public void removingFootballers_GetsThemOffTheTeam() {
        addFootballersToTeam();
        removeFootballersFromTeam();
        assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingFootballer_WhoIsNotOnTheTeam_Throws() {
        addFootballersToTeam();
        team.removeFootballer(anotherStriker.getName());
    }

    @Test
    public void puttingFootballerForSale_MakesThemInactive() {
        addFootballersToTeam();
        team.footballerForSale(striker.getName());
        assertFalse(striker.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void puttingFootballerForSale_WhoIsNotOnTheTeam_Throws() {
        addFootballersToTeam();
        team.footballerForSale(anotherStriker.getName());
    }

    @Test
    public void printingStatisticsInRightFormat() {
        String rightStatistics = addFootballersAndReturnStatistics();
        assertEquals(rightStatistics, team.getStatistics());
    }
}
