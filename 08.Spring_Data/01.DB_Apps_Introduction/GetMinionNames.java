package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connect();

        int minionId = new Scanner(System.in).nextInt();

        PreparedStatement statement = connection.prepareStatement(Query.GET_VILLAIN_NAME_BY_ID);
        statement.setInt(1, minionId);

        ResultSet villainNameRecord = statement.executeQuery();

        if (!villainNameRecord.next()) {
            System.out.println("No villain with ID " + minionId + " exists in the database.");
            return;
        }

        String villainName = villainNameRecord.getString(ColumnLabel.NAME);

        statement = connection.prepareStatement(Query.GET_MINIONS_BY_VILLAIN_ID);
        statement.setInt(1, minionId);

        ResultSet minionRecords = statement.executeQuery();

        System.out.println("Villain: " + villainName);

        for (int i = 1; minionRecords.next(); i++) {
            String minionName = minionRecords.getString(ColumnLabel.NAME);
            String minionAge = minionRecords.getString(ColumnLabel.AGE);
            System.out.println(i + ". " + minionName + " " + minionAge);
        }

        connection.close();
    }
}
