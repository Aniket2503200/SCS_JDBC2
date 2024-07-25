package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question10 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/par2Ques10?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);
			connection.setAutoCommit(false); // Disable auto-commit for batch processing

			// Create a PreparedStatement object for batch updates
			String sql = "UPDATE products SET product_name = ? WHERE product_id = ?";
			preparedStatement = connection.prepareStatement(sql);

			// Example of batch updates: collect user inputs
			for (int i = 0; i < 3; i++) { // Adjust the loop to the number of records you want to update
				System.out.print("Enter Product ID to update: ");
				int productId = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				System.out.print("Enter new Product Name: ");
				String productName = scanner.nextLine();

				// Set parameters for the current batch
				preparedStatement.setString(1, productName);
				preparedStatement.setInt(2, productId);

				// Add to batch
				preparedStatement.addBatch();
			}

			// Execute batch update
			int[] updateCounts = preparedStatement.executeBatch();
			connection.commit(); // Commit changes if successful
			System.out.println("Batch update successful. Rows affected: " + updateCounts.length);

		} catch (SQLException e) {
			System.err.println("Batch update failed. Rolling back changes...");
			if (connection != null) {
				try {
					connection.rollback(); // Rollback in case of failure
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the PreparedStatement and Connection
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
	}
}
