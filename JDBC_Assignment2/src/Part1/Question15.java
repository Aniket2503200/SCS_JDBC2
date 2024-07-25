package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question15 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques15?useSSL=false", "root",
					"oneplus11R");

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Prepare the SQL statement
			String sql = "UPDATE batch_update_table SET amount = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);

			boolean continueUpdating = true;
			while (continueUpdating) {
				// Ask user for the id of the row to update
				System.out.print("Enter id of the row to update: ");
				int id = scanner.nextInt();

				// Ask user for the new amount
				System.out.print("Enter new amount for id " + id + ": ");
				int newAmount = scanner.nextInt();

				// Set parameters and add to batch
				preparedStatement.setInt(1, newAmount);
				preparedStatement.setInt(2, id);
				preparedStatement.addBatch();

				// Check if the user wants to continue
				System.out.print("Do you want to update another row? (yes/no): ");
				scanner.nextLine(); // Consume newline
				String response = scanner.nextLine();
				if (!"yes".equalsIgnoreCase(response)) {
					continueUpdating = false;
				}
			}

			// Execute the batch update
			preparedStatement.executeBatch();

			// Commit the transaction
			connection.commit();
			System.out.println("Batch update committed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					// Roll back the transaction if there is an error
					System.out.println("Rolling back the transaction.");
					connection.rollback();
				}
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
		} finally {
			// Close the resources
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
