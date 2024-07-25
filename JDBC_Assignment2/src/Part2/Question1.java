package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question1 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part2Ques1?useSSL=false", "root",
					"oneplus11R");

			// Create a Statement object
			statement = connection.createStatement();

			// Get user input for the new record
			System.out.print("Enter name: ");
			String name = scanner.nextLine();
			System.out.print("Enter position: ");
			String position = scanner.nextLine();
			System.out.print("Enter salary: ");
			double salary = scanner.nextDouble();
			System.out.print("Enter hire date (YYYY-MM-DD): ");
			String hireDate = scanner.next();

			// Formulate SQL INSERT query
			String sql = String.format(
					"INSERT INTO employees (name, position, salary, hire_date) VALUES ('%s', '%s', %.2f, '%s')", name,
					position, salary, hireDate);

			// Execute the INSERT query
			int rowsAffected = statement.executeUpdate(sql);

			// Check if the record was inserted successfully
			if (rowsAffected > 0) {
				System.out.println("Record inserted successfully.");
			} else {
				System.out.println("Record insertion failed.");
			}

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found: " + e.getMessage());
		} finally {
			// Close resources
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (SQLException e) {
				System.err.println("Error Closing Resources: " + e.getMessage());
			}
		}
	}
}
