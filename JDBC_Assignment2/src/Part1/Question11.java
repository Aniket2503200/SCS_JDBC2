package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question11 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques11?useSSL=false", "root",
					"oneplus11R");

			// Create a statement with scrollable and updatable ResultSet
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// Execute a query
			String sql = "SELECT * FROM navigation_table";
			resultSet = statement.executeQuery(sql);

			// Move forward through the ResultSet
			System.out.println("Moving forward through the ResultSet:");
			while (resultSet.next()) {
				printCurrentRow(resultSet);
			}

			// Move backward through the ResultSet
			System.out.println("\nMoving backward through the ResultSet:");
			while (resultSet.previous()) {
				printCurrentRow(resultSet);
			}

			// Move to a specific row (e.g., 2nd row)
			if (resultSet.absolute(2)) {
				System.out.println("\nMoving to the 2nd row:");
				printCurrentRow(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void printCurrentRow(ResultSet resultSet) throws Exception {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		int age = resultSet.getInt("age");
		String job = resultSet.getString("job");
		System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Job: " + job);
	}
}
