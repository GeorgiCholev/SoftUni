package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainNames {

    private static final int MINIONS_COUNT = 15;

    public static void main(String[] args) throws SQLException {
        final Connection connection = Connector.connect();

        final PreparedStatement statement =
                connection.prepareStatement(Query.GET_VILLAINS_BY_MINION_COUNT);

        statement.setInt(1, MINIONS_COUNT);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString(ColumnLabel.NAME);
            String minionCount = resultSet.getString(ColumnLabel.MINION_COUNT);

            System.out.println(villainName + " " + minionCount);
        }
        connection.close();
    }
}
