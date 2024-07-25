package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question14 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques14?useSSL=false";
		String username = "root";
		String password = "oneplus11R";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a PreparedStatement object for executing SQL
			String sql = "DELETE FROM students WHERE age < ?";
			preparedStatement = connection.prepareStatement(sql);

			// Set the age condition
			preparedStatement.setInt(1, 20);

			// Execute DELETE statement
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Number of students deleted: " + rowsAffected);

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
