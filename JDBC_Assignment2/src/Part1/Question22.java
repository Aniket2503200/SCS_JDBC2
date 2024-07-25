package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question22 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques22?useSSL=false", "root",
					"oneplus11R");

			// Get user input
			System.out.print("Enter name: ");
			String name = scanner.nextLine();
			System.out.print("Enter age: ");
			int age = scanner.nextInt();
			scanner.nextLine(); // Consume newline left-over
			System.out.print("Enter email: ");
			String email = scanner.nextLine();
			System.out.print("Enter address: ");
			String address = scanner.nextLine();
			System.out.print("Enter phone number: ");
			String phoneNumber = scanner.nextLine();

			// Prepare SQL statement with parameterized query
			String sql = "INSERT INTO your_table (name, age, email, address, phone_number) VALUES (?, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);
			pstmt.setString(4, address);
			pstmt.setString(5, phoneNumber);

			// Execute the insert
			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Record inserted successfully. Rows affected: " + rowsAffected);

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
