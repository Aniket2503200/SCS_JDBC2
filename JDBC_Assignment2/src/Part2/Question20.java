package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question20 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/part2Ques20?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		try {
			// Perform CRUD operations
			createUser("john_doe", "john.doe@example.com");
			readUser(1);
			updateUser(1, "john_doe_updated", "john.doe.updated@example.com");
			deleteUser(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Create a new user
	private static void createUser(String username, String email) throws SQLException {
		String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, email);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("User created successfully. Rows affected: " + rowsAffected);
		}
	}

	// Read user details
	private static void readUser(int userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("User ID: " + rs.getInt("user_id"));
				System.out.println("Username: " + rs.getString("username"));
				System.out.println("Email: " + rs.getString("email"));
			} else {
				System.out.println("No user found with ID " + userId);
			}
		}
	}

	// Update user details
	private static void updateUser(int userId, String newUsername, String newEmail) throws SQLException {
		String sql = "UPDATE users SET username = ?, email = ? WHERE user_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, newUsername);
			stmt.setString(2, newEmail);
			stmt.setInt(3, userId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("User updated successfully. Rows affected: " + rowsAffected);
		}
	}

	// Delete a user
	private static void deleteUser(int userId) throws SQLException {
		String sql = "DELETE FROM users WHERE user_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("User deleted successfully. Rows affected: " + rowsAffected);
		}
	}
}
