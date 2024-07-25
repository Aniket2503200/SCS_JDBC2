CREATE DATABASE IF NOT EXISTS part1Ques7;

USE part1Ques7;

CREATE TABLE IF NOT EXISTS your_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT
);

-- Insert some sample data
INSERT INTO your_table (name, age) VALUES ('John Doe', 30);
INSERT INTO your_table (name, age) VALUES ('Jane Smith', 25);
INSERT INTO your_table (name, age) VALUES ('Mike Johnson', 35);
