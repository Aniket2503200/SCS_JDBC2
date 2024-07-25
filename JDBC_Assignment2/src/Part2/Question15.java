package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question15 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques15?useSSL=false";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;
		Statement statement = null;

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a Statement object for executing SQL
			statement = connection.createStatement();

			// SQL query to truncate the table
			String sql = "TRUNCATE TABLE products";

			// Execute TRUNCATE statement
			statement.executeUpdate(sql);
			System.out.println("Table 'products' truncated successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close the Statement and Connection
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
