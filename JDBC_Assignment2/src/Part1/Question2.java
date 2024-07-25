package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question2 {

	// JDBC URL, username, and password of MySQL server
	private static final String URL = "jdbc:mysql://localhost:3306/part1Ques2";
	private static final String USER = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;

		try {
			// 1. Load and Register JDBC Driver (optional step for newer JDBC versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Create a Connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			// 3. Create a Statement Object
			statement = connection.createStatement();

			// 4. Execute a simple query (e.g., create a table)
			String sql = "CREATE TABLE IF NOT EXISTS example_table (" + "id INT NOT NULL AUTO_INCREMENT, "
					+ "name VARCHAR(50) NOT NULL, " + "PRIMARY KEY (id))";
			statement.executeUpdate(sql);
			System.out.println("Table created successfully!");

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQL Exception occurred.");
			e.printStackTrace();
		} finally {
			// 5. Close the Resources
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
