package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question12 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques12?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		Scanner scanner = new Scanner(System.in);

		Connection connection = null;
		Statement statement = null;

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a Statement object for executing SQL
			statement = connection.createStatement();

			// Get employee ID to delete from user input
			System.out.print("Enter Employee ID to delete: ");
			int employeeId = scanner.nextInt();

			// Create SQL DELETE statement
			String sql = "DELETE FROM employees WHERE employee_id = " + employeeId;

			// Execute DELETE statement
			int rowsAffected = statement.executeUpdate(sql);
			if (rowsAffected > 0) {
				System.out.println("Employee with ID " + employeeId + " deleted successfully.");
			} else {
				System.out.println("No employee found with ID " + employeeId);
			}

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
			scanner.close();
		}
	}
}
