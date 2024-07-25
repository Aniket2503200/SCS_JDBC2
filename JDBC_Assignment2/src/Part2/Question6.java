package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Question6 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques6?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		Scanner scanner = new Scanner(System.in);

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Prompt user for employee ID
			System.out.print("Enter the Employee ID of the record you want to retrieve: ");
			int employeeId = scanner.nextInt();

			// Create a PreparedStatement object
			String sql = "SELECT employee_id, first_name, last_name, salary FROM employees WHERE employee_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeId);

			// Execute the query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Display the result
			if (resultSet.next()) {
				System.out.printf("%-10s %-20s %-20s %-10s%n", "Emp ID", "First Name", "Last Name", "Salary");
				System.out.println("------------------------------------------------------------");

				int retrievedEmployeeId = resultSet.getInt("employee_id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				double salary = resultSet.getDouble("salary");

				System.out.printf("%-10d %-20s %-20s %-10.2f%n", retrievedEmployeeId, firstName, lastName, salary);
			} else {
				System.out.println("No record found with the provided Employee ID.");
			}

			// Close the ResultSet, PreparedStatement, and Connection
			resultSet.close();
			preparedStatement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
