package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question11 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques11?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		double incrementAmount = 5000.00; // Amount to increment salary by

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a PreparedStatement object for updating salary
			String sql = "UPDATE employees SET salary = salary + ?";
			preparedStatement = connection.prepareStatement(sql);

			// Set the increment amount
			preparedStatement.setDouble(1, incrementAmount);

			// Execute the update
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Salary increment successful. Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close the PreparedStatement and Connection
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
