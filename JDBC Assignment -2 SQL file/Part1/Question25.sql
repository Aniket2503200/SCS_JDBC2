-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques25;

-- Use the created database
USE part1Ques25;

-- Create a table with sample columns
CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE
);

select *from employees;
