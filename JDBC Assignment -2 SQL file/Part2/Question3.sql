-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS part2Ques3;

-- Use the created database
USE part2Ques3;

-- Create the projects table with the necessary columns
CREATE TABLE IF NOT EXISTS projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    budget DECIMAL(15, 2)
);

-- Insert sample data into the projects table (optional)
INSERT INTO projects (project_name, start_date, end_date, budget) VALUES 
('Website Redesign', '2024-01-15', '2024-06-30', 50000.00),
('Mobile App Development', '2024-03-01', '2024-09-30', 75000.00),
('Database Migration', '2024-05-01', '2024-08-15', 30000.00),
('Cloud Integration', '2024-07-01', '2024-12-31', 100000.00);

select *from projects;