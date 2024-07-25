package Part1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Question17 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		CallableStatement callableStatement = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques17?useSSL=false", "root",
					"oneplus11R");

			// Prompt user for input
			System.out.print("Enter user ID: ");
			int userId = scanner.nextInt();

			// Prepare the callable statement to call the stored procedure
			String sql = "{CALL getUserDetails(?, ?, ?)}";
			callableStatement = connection.prepareCall(sql);

			// Set input parameter
			callableStatement.setInt(1, userId);

			// Register output parameters
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.INTEGER);

			// Execute the stored procedure
			callableStatement.execute();

			// Retrieve output parameters
			String userName = callableStatement.getString(2);
			int userAge = callableStatement.getInt(3);

			// Display the result
			System.out.println("User Name: " + userName);
			System.out.println("User Age: " + userAge);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (callableStatement != null)
					callableStatement.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
