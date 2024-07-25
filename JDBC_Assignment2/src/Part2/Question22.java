package Part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Question22 {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/part2Ques22?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "oneplus11R";

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Select an operation:");
				System.out.println("1. Create Department");
				System.out.println("2. Create Employee");
				System.out.println("3. Read Employee");
				System.out.println("4. Update Employee");
				System.out.println("5. Delete Employee");
				System.out.println("6. Delete Department");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				switch (choice) {
				case 1:
					System.out.print("Enter Department Name: ");
					String deptName = scanner.nextLine();
					createDepartment(deptName);
					break;
				case 2:
					System.out.print("Enter Employee Name: ");
					String empName = scanner.nextLine();
					System.out.print("Enter Department ID: ");
					int deptId = scanner.nextInt();
					createEmployee(empName, deptId);
					break;
				case 3:
					System.out.print("Enter Employee ID: ");
					int empId = scanner.nextInt();
					readEmployee(empId);
					break;
				case 4:
					System.out.print("Enter Employee ID to update: ");
					int updateEmpId = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					System.out.print("Enter new Employee Name: ");
					String newEmpName = scanner.nextLine();
					System.out.print("Enter new Department ID: ");
					int newDeptId = scanner.nextInt();
					updateEmployee(updateEmpId, newEmpName, newDeptId);
					break;
				case 5:
					System.out.print("Enter Employee ID to delete: ");
					int deleteEmpId = scanner.nextInt();
					deleteEmployee(deleteEmpId);
					break;
				case 6:
					System.out.print("Enter Department ID to delete: ");
					int deleteDeptId = scanner.nextInt();
					deleteDepartment(deleteDeptId);
					break;
				case 7:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice, please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Create a new department
	private static void createDepartment(String departmentName) throws SQLException {
		String sql = "INSERT INTO departments (department_name) VALUES (?)";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, departmentName);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Department created successfully. Rows affected: " + rowsAffected);
		}
	}

	// Create a new employee
	private static void createEmployee(String employeeName, int departmentId) throws SQLException {
		// Check if department exists
		String checkDeptSql = "SELECT COUNT(*) FROM departments WHERE department_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement checkDeptStmt = connection.prepareStatement(checkDeptSql)) {
			checkDeptStmt.setInt(1, departmentId);
			ResultSet rs = checkDeptStmt.executeQuery();
			if (rs.next() && rs.getInt(1) == 0) {
				System.out.println("Department ID " + departmentId + " does not exist.");
				return;
			}
		}

		// Proceed with creating the employee
		String sql = "INSERT INTO employees (employee_name, department_id) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, employeeName);
			stmt.setInt(2, departmentId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Employee created successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.err.println("SQL Exception while creating employee: " + e.getMessage());
		}
	}

	// Read employee details
	private static void readEmployee(int employeeId) throws SQLException {
		String sql = "SELECT e.employee_id, e.employee_name, d.department_name FROM employees e "
				+ "JOIN departments d ON e.department_id = d.department_id " + "WHERE e.employee_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Employee ID: " + rs.getInt("employee_id"));
				System.out.println("Employee Name: " + rs.getString("employee_name"));
				System.out.println("Department: " + rs.getString("department_name"));
			} else {
				System.out.println("No employee found with ID " + employeeId);
			}
		}
	}

	// Update employee details
	private static void updateEmployee(int employeeId, String newEmployeeName, int newDepartmentId)
			throws SQLException {
		// Check if new department exists
		String checkDeptSql = "SELECT COUNT(*) FROM departments WHERE department_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement checkDeptStmt = connection.prepareStatement(checkDeptSql)) {
			checkDeptStmt.setInt(1, newDepartmentId);
			ResultSet rs = checkDeptStmt.executeQuery();
			if (rs.next() && rs.getInt(1) == 0) {
				System.out.println("Department ID " + newDepartmentId + " does not exist.");
				return;
			}
		}

		// Proceed with updating the employee
		String sql = "UPDATE employees SET employee_name = ?, department_id = ? WHERE employee_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, newEmployeeName);
			stmt.setInt(2, newDepartmentId);
			stmt.setInt(3, employeeId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Employee updated successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.err.println("SQL Exception while updating employee: " + e.getMessage());
		}
	}

	// Delete an employee
	private static void deleteEmployee(int employeeId) throws SQLException {
		String sql = "DELETE FROM employees WHERE employee_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, employeeId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Employee deleted successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.err.println("SQL Exception while deleting employee: " + e.getMessage());
		}
	}

	// Delete a department
	private static void deleteDepartment(int departmentId) throws SQLException {
		String sql = "DELETE FROM departments WHERE department_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, departmentId);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Department deleted successfully. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.err.println("SQL Exception while deleting department: " + e.getMessage());
		}
	}
}
