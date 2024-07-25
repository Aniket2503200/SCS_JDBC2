CREATE DATABASE part1Ques17;
USE part1Ques17;

CREATE TABLE your_table (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

INSERT INTO your_table (id, name, age) VALUES (1, 'Alice', 25);
INSERT INTO your_table (id, name, age) VALUES (2, 'Bob', 30);
INSERT INTO your_table (id, name, age) VALUES (3, 'Charlie', 35);

DELIMITER //

CREATE PROCEDURE getUserDetails(IN userId INT, OUT userName VARCHAR(50), OUT userAge INT)
BEGIN
    SELECT name, age INTO userName, userAge
    FROM your_table
    WHERE id = userId;
END //

DELIMITER ;
