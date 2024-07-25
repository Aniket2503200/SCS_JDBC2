package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Question8 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques8?useSSL=false", "root",
					"oneplus11R");

			// Get user input for the ID
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter ID to fetch: ");
			int id = scanner.nextInt();

			// Create a prepared statement
			String sql = "SELECT * FROM new_table WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

			// Process the result set
			while (resultSet.next()) {
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
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
