-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques22;

-- Use the created database
USE part1Ques22;

-- Create a table with 6 columns
CREATE TABLE IF NOT EXISTS your_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

select *from your_table;