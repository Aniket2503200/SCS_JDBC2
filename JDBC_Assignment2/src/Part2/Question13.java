package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question13 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques13?useSSL=false";
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

			// Create a PreparedStatement object for executing SQL
			String sql = "DELETE FROM departments WHERE department_id = ?";
			preparedStatement = connection.prepareStatement(sql);

			// Get department ID to delete from user input
			System.out.print("Enter Department ID to delete: ");
			int departmentId = scanner.nextInt();

			// Set the department ID parameter
			preparedStatement.setInt(1, departmentId);

			// Execute DELETE statement
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Department with ID " + departmentId + " deleted successfully.");
			} else {
				System.out.println("No department found with ID " + departmentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
