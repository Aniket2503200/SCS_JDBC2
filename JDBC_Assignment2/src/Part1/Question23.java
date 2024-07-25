package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question23 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques23?useSSL=false", "root",
					"oneplus11R");

			// Get user input for deletion
			System.out.print("Enter the ID of the record to delete: ");
			int id = scanner.nextInt();

			// Prepare SQL statement with parameterized query for deletion
			String sql = "DELETE FROM your_table WHERE id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);

			// Execute the deletion
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Record with ID " + id + " deleted successfully.");
			} else {
				System.out.println("No record found with ID: " + id);
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
