package _1_Intro.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.connect();

        PreparedStatement statement =
                connection.prepareStatement(Query.SET_TOWN_NAMES_TO_UPPERCASE_FOR_COUNTRY);

        String countryName = new Scanner(System.in).nextLine();

        statement.setString(1, countryName);
        boolean rowsNotChanged = statement.executeUpdate() == 0;

        if (rowsNotChanged) {
            System.out.print("No town names were affected.");
            connection.close();
            return;
        }

        statement = connection.prepareStatement(Query.GET_TOWN_NAMES_FOR_COUNTRY);
        statement.setString(1, countryName);
        ResultSet townSet = statement.executeQuery();

        List<String> townsChanged = new ArrayList<>();

        while (townSet.next()) {
            String town = townSet.getString(ColumnLabel.NAME);
            townsChanged.add(town);
        }

        String townOutputFormat = "[" + String.join(", ", townsChanged) + "]";

        System.out.print(townsChanged.size() + " town names were affected. \n" + townOutputFormat);
        connection.close();
    }
}
