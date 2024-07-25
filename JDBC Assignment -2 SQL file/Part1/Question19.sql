-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques19;

-- Use the created database
USE part1Ques19;

-- Create a table
CREATE TABLE IF NOT EXISTS your_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    column_name VARCHAR(255) NOT NULL
);

-- Insert sample data
INSERT INTO your_table (column_name) VALUES ('Sample Data 1');
INSERT INTO your_table (column_name) VALUES ('Sample Data 2');
INSERT INTO your_table (column_name) VALUES ('Sample Data 3');

-- Sample query to test
-- This query will select all rows where id = 1
