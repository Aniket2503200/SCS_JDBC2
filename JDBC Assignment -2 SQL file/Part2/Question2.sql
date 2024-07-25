-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS part2Ques2;

-- Use the created database
USE part2Ques2;

-- Create the departments table with the necessary columns
CREATE TABLE IF NOT EXISTS departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

-- Insert sample data into the departments table (optional)
INSERT INTO departments (department_name, location) VALUES 
('Human Resources', 'New York'),
('Finance', 'Chicago'),
('IT', 'San Francisco'),
('Marketing', 'Los Angeles');

select *from departments;