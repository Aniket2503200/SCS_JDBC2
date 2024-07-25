package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question3 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the thin driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection with SSL disabled
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques3?useSSL=false", "root",
					"oneplus11R");

			// Execute a simple query
			statement = connection.createStatement();
			String sql = "SELECT * FROM your_table";
			resultSet = statement.executeQuery(sql);

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				int marks = resultSet.getInt("marks");
				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + " , Marks: " + marks);
			}

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
