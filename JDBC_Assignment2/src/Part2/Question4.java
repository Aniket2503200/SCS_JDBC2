package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question4 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques4?useSSL=false";
		String username = "root";
		String password = "oneplus11R";

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Create a Statement object
			Statement statement = connection.createStatement();

			// Execute a query to retrieve all records from the table
			String sql = "SELECT id, name, age FROM your_table";
			ResultSet resultSet = statement.executeQuery(sql);

			// Display the results in a formatted manner
			System.out.printf("%-5s %-20s %-5s%n", "ID", "Name", "Age");
			System.out.println("----------------------------------------");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				System.out.printf("%-5d %-20s %-5d%n", id, name, age);
			}

			// Close the ResultSet, Statement, and Connection
			resultSet.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
