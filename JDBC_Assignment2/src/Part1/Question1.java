package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question1 {

	// JDBC URL, username, and password of MySQL server
	private static final String URL = "jdbc:mysql://localhost:3306/part1Ques1";
	private static final String USER = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;

		try {
			// Register the JDBC driver (optional step for newer JDBC versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			// Execute a query
			statement = connection.createStatement();
			String sql = "SELECT id, name, age FROM your_table";
			ResultSet resultSet = statement.executeQuery(sql);

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			}

			// Clean up environment
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources in the finally block to ensure they are closed even if an
			// exception occurs
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
