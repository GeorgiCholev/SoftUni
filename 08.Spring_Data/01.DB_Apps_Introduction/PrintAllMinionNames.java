package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connect();

        PreparedStatement statement = connection.prepareStatement(Query.TOTAL_MINION_COUNT);
        ResultSet setOfMinionCount = statement.executeQuery();
        setOfMinionCount.next();
        int totalMinionCount = setOfMinionCount.getInt(ColumnLabel.MINION_COUNT);

        statement = connection.prepareStatement(Query.SELECT_ALL_MINION_NAMES,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet minionSet = statement.executeQuery();

        Set<String> allMinionNames = new LinkedHashSet<>();

        int iterations = (totalMinionCount / 2);
        for (int i = 1; i <= iterations; i++) {
            minionSet.absolute(i);
            allMinionNames.add(minionSet.getString(ColumnLabel.NAME));
            minionSet.absolute(-i);
            allMinionNames.add(minionSet.getString(ColumnLabel.NAME));
        }
        if (totalMinionCount % 2 != 0) {
            minionSet.absolute(iterations + 1);
            allMinionNames.add(minionSet.getString(ColumnLabel.NAME));
        }

        System.out.print(String.join(System.lineSeparator(), allMinionNames));

        connection.close();
    }
}
