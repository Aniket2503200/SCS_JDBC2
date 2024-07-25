create database part1Ques16;

USE part1Ques16;
CREATE TABLE your_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

INSERT INTO your_table (id, name, age) VALUES (1, 'John Doe', 30);
