package Part2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Question7 {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/part2Ques7?useSSL=false";
		String username = "root";
		String password = "oneplus11R";

		try {
			// Load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish connection to the database
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			// Get DatabaseMetaData
			DatabaseMetaData databaseMetaData = connection.getMetaData();

			// Retrieve and display table names
			ResultSet tables = databaseMetaData.getTables(null, null, "%", new String[] { "TABLE" });
			System.out.println("Tables in the database:");
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				System.out.println(tableName);

				// Retrieve and display column names and data types for each table
				ResultSet columns = databaseMetaData.getColumns(null, null, tableName, "%");
				System.out.printf("Columns in table %s:%n", tableName);
				while (columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String dataType = columns.getString("TYPE_NAME");
					int columnSize = columns.getInt("COLUMN_SIZE");
					System.out.printf("  %s - %s(%d)%n", columnName, dataType, columnSize);
				}
				System.out.println();
				columns.close();
			}

			// Close the ResultSet and Connection
			tables.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
