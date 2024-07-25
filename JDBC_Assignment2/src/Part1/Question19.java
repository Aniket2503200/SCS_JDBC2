package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;

public class Question19 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques19?useSSL=false", "root",
					"oneplus11R");

			// Example SQL query
			String sql = "SELECT * FROM your_table WHERE id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, 1);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				// Process result set
				System.out.println("Data: " + resultSet.getString("column_name"));
			}

		} catch (SQLSyntaxErrorException e) {
			System.err.println("SQL Syntax Error: " + e.getMessage());
		} catch (SQLNonTransientConnectionException e) {
			System.err.println("Connection Error: " + e.getMessage());
		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println("Integrity Constraint Violation: " + e.getMessage());
		} catch (SQLTimeoutException e) {
			System.err.println("Query Timeout: " + e.getMessage());
		} catch (SQLDataException e) {
			System.err.println("Data Error: " + e.getMessage());
		} catch (SQLFeatureNotSupportedException e) {
			System.err.println("Feature Not Supported: " + e.getMessage());
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
