package Part1;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Question18 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtRetrieve = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establish a connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/part1Ques18?useSSL=false", "root",
					"oneplus11R");

			// Insert CLOB
			System.out.print("Enter the text to insert as CLOB: ");
			String clobData = scanner.nextLine();

			String insertSQL = "INSERT INTO clob_table (clob_data) VALUES (?)";
			pstmtInsert = connection.prepareStatement(insertSQL);
			pstmtInsert.setString(1, clobData);
			pstmtInsert.executeUpdate();

			System.out.println("Text inserted successfully.");

			// Retrieve CLOB
			System.out.print("Enter the ID to retrieve the CLOB: ");
			int id = scanner.nextInt();
			scanner.nextLine(); // consume the newline

			String retrieveSQL = "SELECT clob_data FROM clob_table WHERE id = ?";
			pstmtRetrieve = connection.prepareStatement(retrieveSQL);
			pstmtRetrieve.setInt(1, id);
			resultSet = pstmtRetrieve.executeQuery();

			if (resultSet.next()) {
				Clob clob = resultSet.getClob("clob_data");
				Reader reader = clob.getCharacterStream();
				BufferedReader bufferedReader = new BufferedReader(reader);

				String line;
				System.out.println("Retrieved CLOB data:");
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
				bufferedReader.close();
			} else {
				System.out.println("No record found with ID: " + id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmtRetrieve != null)
					pstmtRetrieve.close();
				if (pstmtInsert != null)
					pstmtInsert.close();
				if (connection != null)
					connection.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
