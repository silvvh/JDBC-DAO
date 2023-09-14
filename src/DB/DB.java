package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    public static Connection connection = null;
    public static Properties loadProperties() {
        try (FileInputStream sr = new FileInputStream("db.properties")) {
            Properties databaseProperties = new Properties();
            databaseProperties.load(sr);
            return databaseProperties;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) st.close();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Connection startConnection() {
        if (connection == null) {

            try {
                Properties databaseProperties = loadProperties();
                connection = DriverManager.getConnection(databaseProperties.getProperty("url"), databaseProperties);
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}