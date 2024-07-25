package Part1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question24 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques24?useSSL=false", "root",
					"oneplus11R");

			// Get database metadata
			DatabaseMetaData metaData = connection.getMetaData();

			// Fetch and display table names
			System.out.println("Tables:");
			ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				System.out.println("Table Name: " + tableName);

				// Fetch and display column details for each table
				System.out.println("Columns:");
				ResultSet columns = metaData.getColumns(null, null, tableName, "%");
				while (columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");
					int columnSize = columns.getInt("COLUMN_SIZE");

					System.out.println("Column Name: " + columnName);
					System.out.println("Data Type: " + columnType);
					System.out.println("Column Size: " + columnSize);
				}
				columns.close();
				System.out.println();
			}
			tables.close();

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found: " + e.getMessage());
		} finally {
			// Close the connection
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Error Closing Connection: " + e.getMessage());
			}
		}
	}
}
