package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question20 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase?useSSL=false", "root",
					"oneplus11R");

			// Example SQL query
			String sql = "SELECT * FROM your_table WHERE id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 2);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// Process result set
				System.out.println("Data: " + resultSet.getString("column_name"));
			}

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found: " + e.getMessage());
		} finally {
			// Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Error Closing Resources: " + e.getMessage());
			}
		}
	}
}
