package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question16 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/part2Ques16?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";
	private static Connection connection = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			while (true) {
				System.out.println("Select operation:");
				System.out.println("1. Create");
				System.out.println("2. Read");
				System.out.println("3. Update");
				System.out.println("4. Delete");
				System.out.println("5. Exit");

				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					createEmployee();
					break;
				case 2:
					readEmployees();
					break;
				case 3:
					updateEmployee();
					break;
				case 4:
					deleteEmployee();
					break;
				case 5:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void createEmployee() {
		System.out.print("Enter First Name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter Last Name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		System.out.print("Enter Department: ");
		String department = scanner.nextLine();
		System.out.print("Enter Salary: ");
		double salary = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		String sql = "INSERT INTO employees (first_name, last_name, email, department, salary) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, department);
			preparedStatement.setDouble(5, salary);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Employee created successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void readEmployees() {
		String sql = "SELECT * FROM employees";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("employee_id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String department = resultSet.getString("department");
				double salary = resultSet.getDouble("salary");

				System.out.printf("ID: %d, First Name: %s, Last Name: %s, Email: %s, Department: %s, Salary: %.2f%n",
						id, firstName, lastName, email, department, salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateEmployee() {
		System.out.print("Enter Employee ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Enter new First Name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter new Last Name: ");
		String lastName = scanner.nextLine();
		System.out.print("Enter new Email: ");
		String email = scanner.nextLine();
		System.out.print("Enter new Department: ");
		String department = scanner.nextLine();
		System.out.print("Enter new Salary: ");
		double salary = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, department = ?, salary = ? WHERE employee_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, department);
			preparedStatement.setDouble(5, salary);
			preparedStatement.setInt(6, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Employee updated successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteEmployee() {
		System.out.print("Enter Employee ID to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "DELETE FROM employees WHERE employee_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee deleted successfully.");
			} else {
				System.out.println("No employee found with ID " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
