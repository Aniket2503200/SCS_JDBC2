CREATE DATABASE IF NOT EXISTS part1Ques8;

USE part1Ques8;

CREATE TABLE IF NOT EXISTS new_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    address VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100)
);

-- Insert some sample data
INSERT INTO new_table (name, age, address, email, phone, department) VALUES 
('John Doe', 30, '123 Elm St', 'john.doe@example.com', '123-456-7890', 'Engineering'),
('Jane Smith', 25, '456 Oak St', 'jane.smith@example.com', '234-567-8901', 'Marketing'),
('Mike Johnson', 35, '789 Pine St', 'mike.johnson@example.com', '345-678-9012', 'Sales');
