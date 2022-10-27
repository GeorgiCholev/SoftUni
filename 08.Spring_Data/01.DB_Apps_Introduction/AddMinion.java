package _1_Intro.exercise;

import java.sql.*;
import java.util.Scanner;

public class AddMinion {

    private static final String VILLAIN_ADDED_OUTPUT = "Villain %s was added to the database.%n";
    private static final String TOWN_ADDED_OUTPUT = "Town %s was added to the database.%n";

    private static Connection connection;

    private static PreparedStatement statement;

    public static void main(String[] args) throws SQLException {
        connection = Connector.connect();

        Scanner s = new Scanner(System.in);

        String[] minionInfo = s.nextLine().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String townName = minionInfo[3];

        String villainName = s.nextLine().split("\\s+")[1];

        int townId = getIdFor(townName, Query.INSERT_NAME_IN_TOWNS,
                TOWN_ADDED_OUTPUT, Query.FIND_ID_IN_TABLE_TOWNS);

        int villainId = getIdFor(villainName, Query.INSERT_NAME_AND_DEFAULT_EVIL_IN_VILLAINS,
                VILLAIN_ADDED_OUTPUT, Query.FIND_ID_IN_TABLE_VILLAINS);

        insertMinion(minionName, minionAge, townId);

        int minionId = getMinionId(minionName);

        insertMinionVillainPair(villainId, minionId);

        System.out.println("Successfully added " + minionName + " to be minion of " + villainName + ".");

        connection.close();
    }

    private static void insertMinionVillainPair(int villainId, int minionId) throws SQLException {
        statement = connection.prepareStatement(Query.INSERT_PAIR_IN_MINIONS_VILLAINS);
        statement.setInt(1, minionId);
        statement.setInt(2, villainId);
        statement.executeUpdate();
    }

    private static int getMinionId(String minionName) throws SQLException {
        ResultSet minionSet = executeFindQuery(minionName, Query.FIND_ID_IN_TABLE_MINIONS);
        minionSet.next();
        return minionSet.getInt(ColumnLabel.ID);
    }

    private static void insertMinion(String minionName, int minionAge, int townId) throws SQLException {
        statement = connection.prepareStatement(Query.INSERT_MINION);
        statement.setString(1, minionName);
        statement.setInt(2, minionAge);
        statement.setInt(3, townId);
        statement.executeUpdate();
    }

    private static int getIdFor
            (String paramName, String insertQuery, String output, String findQuery) throws SQLException {

        ResultSet searchedRecord = executeFindQuery(paramName, findQuery);

        insertRecordIfNotPresent(searchedRecord, paramName, insertQuery, output);

        searchedRecord = executeFindQuery(paramName, findQuery);

        searchedRecord.next();
        return searchedRecord.getInt(ColumnLabel.ID);
    }

    private static void insertRecordIfNotPresent
            (ResultSet record, String paramName, String insertQuery, String output) throws SQLException {
        if (!record.next()) {
            executeInsertQuery(paramName, insertQuery);
            System.out.printf(output, paramName);
        }
    }

    private static ResultSet executeFindQuery(String paramName, String query) throws SQLException {
        statement = connection.prepareStatement(query);
        statement.setString(1, paramName);
        return statement.executeQuery();
    }

    private static void executeInsertQuery(String name, String query) throws SQLException {
        statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.executeUpdate();
    }
}
