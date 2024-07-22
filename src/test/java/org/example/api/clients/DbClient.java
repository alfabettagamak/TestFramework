package org.example.api.clients;

import java.sql.*;

public class DbClient {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ololo", "postgres", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int createTableRequest(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return statement.getUpdateCount();
    }

    public static ResultSet getDataTable(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        result.next();
        return result;
    }
}
