package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        int[] minionIds = Arrays.stream(new Scanner(System.in).nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String[] parameterSlotsForQuery = new String[minionIds.length];
        Arrays.fill(parameterSlotsForQuery, "?");

        String updateQuery = Query.NOT_PARAMETRIZED_UPDATE_MINION + " (" +
                String.join(", ", parameterSlotsForQuery) + ");";

        Connection connection = Connector.connect();

        PreparedStatement statement = connection.prepareStatement(updateQuery);
        for (int i = 0; i < parameterSlotsForQuery.length; i++) {
            statement.setInt(i + 1, minionIds[i]);
        }

        statement.executeUpdate();

        statement = connection.prepareStatement(Query.SELECT_ALL_MINIONS);
        ResultSet allMinionsSet = statement.executeQuery();

        List<String> allMinions = new ArrayList<>();
        while (allMinionsSet.next()) {
            String minionName = allMinionsSet.getString(ColumnLabel.NAME);
            String minionAge = allMinionsSet.getString(ColumnLabel.AGE);

            allMinions.add(minionName + " " + minionAge);
        }

        System.out.print(String.join(System.lineSeparator(), allMinions));

        connection.close();
    }
}
