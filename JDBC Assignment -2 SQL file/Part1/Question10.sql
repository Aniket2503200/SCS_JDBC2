CREATE DATABASE IF NOT EXISTS part1Ques10;

USE part1Ques10;

CREATE TABLE IF NOT EXISTS your_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    job VARCHAR(100)
);

-- Insert some sample data
INSERT INTO your_table (name, age, job) VALUES 
('John Doe', 30, 'Engineer'),
('Jane Smith', 25, 'Designer'),
('Mike Johnson', 35, 'Manager');