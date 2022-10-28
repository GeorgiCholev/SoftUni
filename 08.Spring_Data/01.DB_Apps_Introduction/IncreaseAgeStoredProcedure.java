package _1_Intro.exercise;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    //DELIMITER $$
    //CREATE PROCEDURE usp_get_older(minion_id INT)
    //BEGIN
    //    UPDATE minions SET age = 1 + age WHERE id = minion_id;
    //END$$

    public static void main(String[] args) throws SQLException {
        int minionId = new Scanner(System.in).nextInt();

        Connection connection = Connector.connect();
        CallableStatement callableStatement = connection.prepareCall(Query.CALL_USP_GET_OLDER);
        callableStatement.setInt(1, minionId);
        callableStatement.execute();

        PreparedStatement statement = connection.prepareStatement(Query.SELECT_MINION_AGE_AND_NAME_BY_ID);
        statement.setInt(1, minionId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        String minionName = resultSet.getString(ColumnLabel.NAME);
        String minionAge = resultSet.getString(ColumnLabel.AGE);

        System.out.print(minionName + " " + minionAge);

        connection.close();
    }
}
