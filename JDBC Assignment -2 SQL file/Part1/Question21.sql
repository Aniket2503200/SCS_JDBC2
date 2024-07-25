-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques21;

-- Use the created database
USE part1Ques21;

-- Create a table with 6 columns
CREATE TABLE IF NOT EXISTS your_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

-- Insert sample data into the table
INSERT INTO your_table (name, age, email, address, phone_number) VALUES 
('Alice Johnson', 30, 'alice.johnson@example.com', '123 Elm Street', '555-1234'),
('Bob Smith', 45, 'bob.smith@example.com', '456 Oak Avenue', '555-5678'),
('Carol Williams', 29, 'carol.williams@example.com', '789 Pine Road', '555-8765');
