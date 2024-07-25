CREATE DATABASE IF NOT EXISTS part1Ques9;

USE part1Ques9;

CREATE TABLE IF NOT EXISTS detailed_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    address VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100)
);

-- Insert sample data
INSERT INTO detailed_table (name, age, address, email, phone, department) VALUES 
('Alice Johnson', 28, '101 Maple St', 'alice.johnson@example.com', '111-222-3333', 'HR'),
('Bob Brown', 32, '202 Birch St', 'bob.brown@example.com', '222-333-4444', 'Finance'),
('Charlie Davis', 45, '303 Cedar St', 'charlie.davis@example.com', '333-444-5555', 'IT');
