package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Question17 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/part2Ques17?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";
	private static Connection connection = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			while (true) {
				System.out.println("\nMenu:");
				System.out.println("1. Create Product");
				System.out.println("2. Read Products");
				System.out.println("3. Update Product");
				System.out.println("4. Delete Product");
				System.out.println("5. Exit");

				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					createProduct();
					break;
				case 2:
					readProducts();
					break;
				case 3:
					updateProduct();
					break;
				case 4:
					deleteProduct();
					break;
				case 5:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void createProduct() {
		System.out.print("Enter Product Name: ");
		String productName = scanner.nextLine();
		System.out.print("Enter Product Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		String sql = "INSERT INTO products (product_name, price) VALUES (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, productName);
			preparedStatement.setDouble(2, price);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Product created successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void readProducts() {
		String sql = "SELECT * FROM products";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("product_id");
				String name = resultSet.getString("product_name");
				double price = resultSet.getDouble("price");

				System.out.printf("ID: %d, Name: %s, Price: %.2f%n", id, name, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateProduct() {
		System.out.print("Enter Product ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		System.out.print("Enter new Product Name: ");
		String productName = scanner.nextLine();
		System.out.print("Enter new Product Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		String sql = "UPDATE products SET product_name = ?, price = ? WHERE product_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, productName);
			preparedStatement.setDouble(2, price);
			preparedStatement.setInt(3, id);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Product updated successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteProduct() {
		System.out.print("Enter Product ID to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String sql = "DELETE FROM products WHERE product_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product deleted successfully.");
			} else {
				System.out.println("No product found with ID " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
