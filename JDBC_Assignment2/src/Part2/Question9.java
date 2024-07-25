package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Question9 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques9?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		Scanner scanner = new Scanner(System.in);

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Prompt user for details to update the record
			System.out.print("Enter the Employee ID of the record you want to update: ");
			int employeeId = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			System.out.print("Enter the new First Name: ");
			String firstName = scanner.nextLine();

			System.out.print("Enter the new Last Name: ");
			String lastName = scanner.nextLine();

			System.out.print("Enter the new Email: ");
			String email = scanner.nextLine();

			System.out.print("Enter the new Department: ");
			String department = scanner.nextLine();

			System.out.print("Enter the new Salary: ");
			double salary = scanner.nextDouble();

			// Create a PreparedStatement object
			String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, department = ?, salary = ? WHERE employee_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// Set parameters
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, department);
			preparedStatement.setDouble(5, salary);
			preparedStatement.setInt(6, employeeId);

			// Execute the update
			int rowsAffected = preparedStatement.executeUpdate();

			// Display the result
			if (rowsAffected > 0) {
				System.out.println("Record updated successfully.");
			} else {
				System.out.println("No record found with the provided Employee ID.");
			}

			// Close the PreparedStatement and Connection
			preparedStatement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
