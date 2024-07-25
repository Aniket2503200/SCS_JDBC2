package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Question14 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		Scanner scanner = new Scanner(System.in);

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques14?useSSL=false", "root",
					"oneplus11R");

			// Disable auto-commit mode
			connection.setAutoCommit(false);

			// Prepare SQL statements
			String sql1 = "UPDATE transaction_table SET amount = ? WHERE id = ?";
			String sql2 = "UPDATE transaction_table SET amount = ? WHERE id = ?";

			// Execute the first update
			preparedStatement1 = connection.prepareStatement(sql1);
			System.out.print("Enter new amount for id 1: ");
			int amount1 = scanner.nextInt();
			preparedStatement1.setInt(1, amount1);
			preparedStatement1.setInt(2, 1);
			preparedStatement1.executeUpdate();

			// Execute the second update
			preparedStatement2 = connection.prepareStatement(sql2);
			System.out.print("Enter new amount for id 2: ");
			int amount2 = scanner.nextInt();
			preparedStatement2.setInt(1, amount2);
			preparedStatement2.setInt(2, 2);
			preparedStatement2.executeUpdate();

			// Commit the transaction
			connection.commit();
			System.out.println("Transaction committed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					// Roll back the transaction if there is an error
					System.out.println("Rolling back the transaction.");
					connection.rollback();
				}
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
		} finally {
			// Close the resources
			try {
				if (preparedStatement1 != null)
					preparedStatement1.close();
				if (preparedStatement2 != null)
					preparedStatement2.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
