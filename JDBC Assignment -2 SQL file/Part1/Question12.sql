CREATE DATABASE IF NOT EXISTS part1Ques12;

USE part1Ques12;

CREATE TABLE IF NOT EXISTS update_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    job VARCHAR(100)
);

-- Insert some sample data
INSERT INTO update_table (name, age, job) VALUES 
('John Doe', 30, 'Engineer'),
('Jane Smith', 25, 'Designer'),
('Mike Johnson', 35, 'Manager');

select*from update_table;