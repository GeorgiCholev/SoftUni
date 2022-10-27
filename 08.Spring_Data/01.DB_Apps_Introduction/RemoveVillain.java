package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connect();

        int villainId = new Scanner(System.in).nextInt();

        PreparedStatement statement = connection.prepareStatement(Query.GET_VILLAIN_INFO_BY_ID);
        statement.setInt(1, villainId);
        ResultSet villainSet = statement.executeQuery();

        if (!villainSet.next()) {
            System.out.print("No such villain was found");
            connection.close();
            return;
        }

        String villainName = villainSet.getString(ColumnLabel.NAME);
        String minionCount = villainSet.getString(ColumnLabel.MINION_COUNT);

        updateQueryFor(villainId, Query.REMOVE_VILLAIN_FROM_MINIONS_VILLAINS, connection);
        updateQueryFor(villainId, Query.REMOVE_VILLAIN, connection);

        System.out.print(villainName + " was deleted\n" +
                minionCount + " minions released");

        connection.close();
    }

    private static void updateQueryFor
            (int villainId, String query, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, villainId);
        statement.executeUpdate();

    }
}
