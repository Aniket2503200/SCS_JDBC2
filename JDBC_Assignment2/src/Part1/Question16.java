package Part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Question16 {

	// Simple connection pool class
	static class SimpleConnectionPool {
		private LinkedList<Connection> connectionPool;
		private final int poolSize = 5; // Number of connections in the pool

		public SimpleConnectionPool(String url, String user, String password) throws SQLException {
			connectionPool = new LinkedList<>();
			// Initialize the pool with connections
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				connectionPool.add(connection);
			}
		}

		public synchronized Connection getConnection() throws SQLException {
			if (connectionPool.isEmpty()) {
				throw new SQLException("No available connections in the pool.");
			}
			return connectionPool.removeFirst();
		}

		public synchronized void releaseConnection(Connection connection) {
			connectionPool.addLast(connection);
		}

		public synchronized void closePool() throws SQLException {
			for (Connection connection : connectionPool) {
				connection.close();
			}
			connectionPool.clear();
		}
	}

	public static void main(String[] args) {
		SimpleConnectionPool pool = null;

		try {
			// Initialize the connection pool with SSL disabled
			pool = new SimpleConnectionPool("jdbc:mysql://localhost:3306/part1Ques16?useSSL=false", "root",
					"oneplus11R");

			// Get a connection from the pool
			Connection connection = pool.getConnection();
			System.out.println("Connection established successfully.");

			// Use the connection to perform database operations
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table");

			boolean hasResults = false;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
				hasResults = true;
			}

			if (!hasResults) {
				System.out.println("No results found.");
			}

			// Release the connection back to the pool
			pool.releaseConnection(connection);

		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pool != null) {
					pool.closePool();
				}
			} catch (SQLException e) {
				System.err.println("Error closing connection pool: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
