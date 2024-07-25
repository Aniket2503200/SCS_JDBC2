-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS part2Ques1;

-- Use the created database
USE part2Ques1;

-- Create the employees table with the necessary columns
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE
);

-- Insert sample data into the employees table (optional)
INSERT INTO employees (name, position, salary, hire_date) VALUES 
('John Doe', 'Software Engineer', 80000.00, '2024-01-15'),
('Jane Smith', 'Data Scientist', 90000.00, '2023-06-30'),
('Emily Davis', 'Project Manager', 95000.00, '2022-09-01');

select *from employees;