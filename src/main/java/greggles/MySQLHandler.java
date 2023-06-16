package greggles;

import java.sql.*;

/**
 * Handles all interactions with the MySQL database.
 */
public class MySQLHandler {
    private final String URL = "jdbc:mysql://localhost:3306/LipogramGame";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private static Connection connection;

    /**
     * Connects to the LipogramGame db, wherein consists the tables of words.
     */
    public void connect() {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a word in its original form (i.e not lipogrammed). Does not return
     * previously used words. When a word is selected to be printed, the Chosen flag
     * gets set to 1 so that it is not chosen again.
     * 
     * @param character The lipogram that the word must have in it.
     * @param table     The resource of words selected by the user.
     * @return A word from the table.
     */
    public String getWord(String character, String table) {
        String word = null;
        try {
            // Create the SQL statement
            String sql = "SELECT word FROM " + table + " WHERE chosen = 0 AND word LIKE '%" + character
                    + "%' ORDER BY RAND() LIMIT 1";
            // Create a statement object
            Statement statement = connection.createStatement();
            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
            // Process the result
            if (resultSet.next()) {
                word = resultSet.getString("Word");
                // Mark the word as Chosen
                String updateSql = "UPDATE " + table + " SET Chosen = 1 WHERE Word = '" + word + "'";
                PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.executeUpdate();
                updateStatement.close();
            } else {
                System.out.println("No available words found in the database.");
            }
            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return word;
    }

    /**
     * Sets the chosen words flag back to 0 from 1.
     * 
     * @param table The table that the user has been taking words from.
     */
    public static void ReturnToUnchosen(String table) {
        try {
            // Create the SQL statement
            String sql = "UPDATE " + table + " SET Chosen = 0";
            // Create a statement object
            Statement statement = connection.createStatement();
            // Execute the update statement
            statement.executeUpdate(sql);
            // System.out.println("Reset " + table + " chosen values to 0.");
            // Close the resources
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                // System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}