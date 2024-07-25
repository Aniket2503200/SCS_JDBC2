package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question4 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loaded Successfully!");

			// Establish a connection with SSL disabled
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques4?useSSL=false", "root",
					"oneplus11R");

			// Create a statement
			statement = connection.createStatement();

			// Execute a query to read data
			String sql = "SELECT * FROM your_table";
			resultSet = statement.executeQuery(sql);

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources in the finally block
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
