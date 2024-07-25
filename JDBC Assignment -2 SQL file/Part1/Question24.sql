-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques24;

-- Use the created database
USE part1Ques24;

-- Create a table with sample columns
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE
);

-- Insert sample data into the table
INSERT INTO employees (name, position, salary, hire_date) VALUES 
('Alice Johnson', 'Software Engineer', 85000.00, '2022-01-15'),
('Bob Smith', 'Data Analyst', 75000.00, '2021-06-30'),
('Carol Williams', 'Project Manager', 95000.00, '2020-09-01');

select *from employees;