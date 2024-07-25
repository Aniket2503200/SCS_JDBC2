package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Question12 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques12?useSSL=false", "root",
					"oneplus11R");

			// Create a statement with scrollable and updatable ResultSet
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// Execute a query to get the ResultSet
			String sql = "SELECT * FROM update_table";
			resultSet = statement.executeQuery(sql);

			// Ask user for the ID of the record to update
			System.out.print("Enter the ID of the record to update: ");
			int idToUpdate = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			// Ask user for new values
			System.out.print("Enter new name: ");
			String newName = scanner.nextLine();

			System.out.print("Enter new age: ");
			int newAge = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			System.out.print("Enter new job: ");
			String newJob = scanner.nextLine();

			// Move to the row to be updated and update it
			boolean recordFound = false;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				if (id == idToUpdate) {
					// Update the record
					resultSet.updateString("name", newName);
					resultSet.updateInt("age", newAge);
					resultSet.updateString("job", newJob);

					// Commit the updates
					resultSet.updateRow();
					recordFound = true;
					System.out.println("Record with ID = " + idToUpdate + " has been updated.");
					break;
				}
			}

			if (!recordFound) {
				System.out.println("Record with ID = " + idToUpdate + " not found.");
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
				if (scanner != null)
					scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
