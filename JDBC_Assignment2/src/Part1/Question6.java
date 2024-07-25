package Part1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Question6 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques6?useSSL=false", "root",
					"oneplus11R");

			// Statement example
			System.out.println("Using Statement:");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM your_table");
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name")
						+ ", Age: " + resultSet.getInt("age"));
			}
			resultSet.close();
			statement.close();

			// PreparedStatement example
			System.out.println("\nUsing PreparedStatement:");
			String sql = "SELECT * FROM your_table WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1); // Example with id = 1
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name")
						+ ", Age: " + resultSet.getInt("age"));
			}
			resultSet.close();
			preparedStatement.close();

			// CallableStatement example
			System.out.println("\nUsing CallableStatement:");
			String callSql = "{CALL get_user_by_id(?)}"; // Assume a stored procedure named get_user_by_id
			callableStatement = connection.prepareCall(callSql);
			callableStatement.setInt(1, 1); // Example with id = 1
			resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name")
						+ ", Age: " + resultSet.getInt("age"));
			}
			resultSet.close();
			callableStatement.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
