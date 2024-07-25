package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question3 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part2Ques3?useSSL=false", "root",
					"oneplus11R");

			// Prepare the SQL INSERT query
			String sql = "INSERT INTO projects (project_name, start_date, end_date, budget) VALUES (?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			// Get the number of records to insert
			System.out.print("Enter the number of projects to insert: ");
			int numberOfProjects = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			// Loop to collect user input for each project
			for (int i = 0; i < numberOfProjects; i++) {
				System.out.println("Enter details for project " + (i + 1) + ":");

				System.out.print("Enter project name: ");
				String projectName = scanner.nextLine();
				System.out.print("Enter start date (YYYY-MM-DD): ");
				String startDate = scanner.nextLine();
				System.out.print("Enter end date (YYYY-MM-DD): ");
				String endDate = scanner.nextLine();
				System.out.print("Enter budget: ");
				double budget = scanner.nextDouble();
				scanner.nextLine(); // Consume the newline

				// Set parameters for the current batch
				pstmt.setString(1, projectName);
				pstmt.setString(2, startDate);
				pstmt.setString(3, endDate);
				pstmt.setDouble(4, budget);

				// Add to batch
				pstmt.addBatch();
			}

			// Execute the batch
			int[] result = pstmt.executeBatch();

			// Check if the batch was executed successfully
			int totalRecordsInserted = 0;
			for (int count : result) {
				totalRecordsInserted += count;
			}
			System.out.println(totalRecordsInserted + " records inserted successfully.");

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
