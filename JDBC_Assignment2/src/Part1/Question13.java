package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class Question13 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		Savepoint savepoint1 = null; // Declare savepoint1 outside the try block

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques13?useSSL=false", "root",
					"oneplus11R");

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Create SQL statements
			String sql1 = "INSERT INTO transaction_table (name, amount) VALUES (?, ?)";
			String sql2 = "UPDATE transaction_table SET amount = ? WHERE id = ?";

			// Execute the first SQL statement
			preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setString(1, "Alice");
			preparedStatement1.setInt(2, 100);
			preparedStatement1.executeUpdate();

			// Set a savepoint
			savepoint1 = connection.setSavepoint("Savepoint1");

			// Execute the second SQL statement
			preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, 200);
			preparedStatement2.setInt(2, 1); // Assuming there is a record with id = 1
			preparedStatement2.executeUpdate();

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (connection != null && savepoint1 != null) {
					// Roll back to the savepoint if there is an error
					System.out.println("Rolling back to Savepoint1.");
					connection.rollback(savepoint1);
				}
			} catch (Exception rollbackException) {
				rollbackException.printStackTrace();
			}
		} finally {
			// Close the resources
			try {
				if (preparedStatement1 != null)
					preparedStatement1.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
