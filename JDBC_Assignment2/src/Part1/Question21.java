package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Question21 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques21?useSSL=false", "root",
					"oneplus11R");

			// Prompt user for input
			System.out.print("Enter the ID to retrieve data: ");
			int id = scanner.nextInt();
			scanner.nextLine(); // Consume newline left-over

			// Prepare SQL statement with parameterized query
			String sql = "SELECT * FROM your_table WHERE id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);

			// Execute query and process results
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id"));
				System.out.println("Name: " + resultSet.getString("name"));
				System.out.println("Age: " + resultSet.getInt("age"));
				System.out.println("Email: " + resultSet.getString("email"));
				System.out.println("Address: " + resultSet.getString("address"));
				System.out.println("Phone Number: " + resultSet.getString("phone_number"));
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
				if (resultSet != null)
					resultSet.close();
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
