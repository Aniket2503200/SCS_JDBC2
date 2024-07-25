package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question9 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques9?useSSL=false", "root",
					"oneplus11R");

			// Create a statement
			statement = connection.createStatement();

			// Execute a query
			String sql = "SELECT * FROM detailed_table";
			resultSet = statement.executeQuery(sql);

			// Process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				String department = resultSet.getString("department");

				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Address: " + address
						+ ", Email: " + email + ", Phone: " + phone + ", Department: " + department);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources
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
