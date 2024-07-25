package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question2 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part2Ques2?useSSL=false", "root",
					"oneplus11R");

			// Prepare the SQL INSERT query
			String sql = "INSERT INTO departments (department_name, location) VALUES (?, ?)";
			pstmt = connection.prepareStatement(sql);

			// Get user input for the new record
			System.out.print("Enter department name: ");
			String departmentName = scanner.nextLine();
			System.out.print("Enter location: ");
			String location = scanner.nextLine();

			// Set the values for the PreparedStatement
			pstmt.setString(1, departmentName);
			pstmt.setString(2, location);

			// Execute the INSERT query
			int rowsAffected = pstmt.executeUpdate();

			// Check if the record was inserted successfully
			if (rowsAffected > 0) {
				System.out.println("Department record inserted successfully.");
			} else {
				System.out.println("Department record insertion failed.");
			}

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found: " + e.getMessage());
		} finally {
			// Close resources
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (SQLException e) {
				System.err.println("Error Closing Resources: " + e.getMessage());
			}
		}
	}
}
