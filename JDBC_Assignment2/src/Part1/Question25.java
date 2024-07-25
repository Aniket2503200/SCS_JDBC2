package Part1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question25 {
	private static Connection getConnection() throws SQLException {
		// Load the MySQL JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBC Driver Not Found", e);
		}
		// Establish a connection
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques25?useSSL=false", "root",
				"oneplus11R");
	}

	private static void createRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter position: ");
		String position = scanner.nextLine();
		System.out.print("Enter salary: ");
		double salary = scanner.nextDouble();
		System.out.print("Enter hire date (YYYY-MM-DD): ");
		String hireDate = scanner.next();

		String sql = "INSERT INTO employees (name, position, salary, hire_date) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, position);
			pstmt.setDouble(3, salary);
			pstmt.setDate(4, Date.valueOf(hireDate));
			pstmt.executeUpdate();
			System.out.println("Record created successfully.");
		}
	}

	private static void readRecords(Connection connection) throws SQLException {
		String sql = "SELECT * FROM employees";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String position = rs.getString("position");
				double salary = rs.getDouble("salary");
				Date hireDate = rs.getDate("hire_date");

				System.out.printf("ID: %d, Name: %s, Position: %s, Salary: %.2f, Hire Date: %s%n", id, name, position,
						salary, hireDate);
			}
		}
	}

	private static void updateRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter the ID of the record to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter new name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new position: ");
		String position = scanner.nextLine();
		System.out.print("Enter new salary: ");
		double salary = scanner.nextDouble();
		System.out.print("Enter new hire date (YYYY-MM-DD): ");
		String hireDate = scanner.next();

		String sql = "UPDATE employees SET name = ?, position = ?, salary = ?, hire_date = ? WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, position);
			pstmt.setDouble(3, salary);
			pstmt.setDate(4, Date.valueOf(hireDate));
			pstmt.setInt(5, id);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record updated successfully.");
			} else {
				System.out.println("No record found with ID: " + id);
			}
		}
	}

	private static void deleteRecord(Connection connection, Scanner scanner) throws SQLException {
		System.out.print("Enter the ID of the record to delete: ");
		int id = scanner.nextInt();

		String sql = "DELETE FROM employees WHERE id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record deleted successfully.");
			} else {
				System.out.println("No record found with ID: " + id);
			}
		}
	}

	public static void main(String[] args) {
		try (Connection connection = getConnection(); Scanner scanner = new Scanner(System.in)) {
			boolean running = true;
			while (running) {
				System.out.println("CRUD Application Menu:");
				System.out.println("1. Create Record");
				System.out.println("2. Read Records");
				System.out.println("3. Update Record");
				System.out.println("4. Delete Record");
				System.out.println("5. Exit");
				System.out.print("Choose an option: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					createRecord(connection, scanner);
					break;
				case 2:
					readRecords(connection);
					break;
				case 3:
					updateRecord(connection, scanner);
					break;
				case 4:
					deleteRecord(connection, scanner);
					break;
				case 5:
					running = false;
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
	}
}
