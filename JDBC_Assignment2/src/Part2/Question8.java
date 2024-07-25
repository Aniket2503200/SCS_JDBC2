package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Question8 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques8?useSSL=false";
		String username = "root";
		String password = "oneplus11R";
		Scanner scanner = new Scanner(System.in);

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Prompt user for details to update the record
			System.out.print("Enter the Product ID of the record you want to update: ");
			int productId = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			System.out.print("Enter the new Product Name: ");
			String productName = scanner.nextLine();

			System.out.print("Enter the new Price: ");
			double price = scanner.nextDouble();

			// Create a Statement object
			Statement statement = connection.createStatement();

			// Create SQL update query
			String sql = "UPDATE products SET product_name = '" + productName + "', price = " + price
					+ " WHERE product_id = " + productId;

			// Execute the update
			int rowsAffected = statement.executeUpdate(sql);

			// Display the result
			if (rowsAffected > 0) {
				System.out.println("Record updated successfully.");
			} else {
				System.out.println("No record found with the provided Product ID.");
			}

			// Close the Statement and Connection
			statement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
