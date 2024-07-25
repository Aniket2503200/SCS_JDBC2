package Part1;

import java.sql.Connection;
import java.sql.DriverManager;

public class Question5 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques5?useSSL=false", "root",
					"oneplus11R");
			System.out.println("Connection established successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				if (connection != null) {
					connection.close();
					System.out.println("Connection closed successfully!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
