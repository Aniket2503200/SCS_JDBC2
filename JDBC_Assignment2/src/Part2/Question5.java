package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Question5 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques5?useSSL=false";
		String username = "root";
		String password = "oneplus11R";

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a PreparedStatement object
			String sql = "SELECT id, name, age FROM your_table WHERE age > ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 30);

			// Execute the query
			ResultSet resultSet = preparedStatement.executeQuery();

			// Display the results in a formatted manner
			System.out.printf("%-5s %-20s %-5s%n", "ID", "Name", "Age");
			System.out.println("----------------------------------------");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.printf("%-5d %-20s %-5d%n", id, name, age);
			}

			// Close the ResultSet, PreparedStatement, and Connection
			resultSet.close();
			preparedStatement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
