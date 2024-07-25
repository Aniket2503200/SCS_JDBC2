package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question18 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/part2Ques18?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		Connection connection = null;
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false); // Disable auto-commit to manage transactions

			// Perform a series of CRUD operations
			try {
				// 1. Insert a new employee
				String insertSql = "INSERT INTO employees (first_name, last_name, email, department, salary) VALUES (?, ?, ?, ?, ?)";
				try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
					insertStmt.setString(1, "Diana");
					insertStmt.setString(2, "Wilson");
					insertStmt.setString(3, "diana.wilson@example.com");
					insertStmt.setString(4, "IT");
					insertStmt.setDouble(5, 62000);
					insertStmt.executeUpdate();
				}

				// 2. Update an existing employee
				String updateSql = "UPDATE employees SET salary = ? WHERE first_name = ?";
				try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
					updateStmt.setDouble(1, 63000);
					updateStmt.setString(2, "Diana");
					updateStmt.executeUpdate();
				}

				// 3. Delete an employee
				String deleteSql = "DELETE FROM employees WHERE first_name = ?";
				try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
					deleteStmt.setString(1, "Alice");
					deleteStmt.executeUpdate();
				}

				// Commit the transaction if all operations are successful
				connection.commit();
				System.out.println("Transaction successful: All operations completed.");

			} catch (SQLException e) {
				// Rollback the transaction if any operation fails
				System.err.println("Transaction failed: Rolling back changes...");
				connection.rollback();
				e.printStackTrace();
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
