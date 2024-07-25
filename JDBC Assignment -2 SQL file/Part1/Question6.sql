CREATE DATABASE IF NOT EXISTS part1Ques6;

USE part1Ques6;

CREATE TABLE IF NOT EXISTS your_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT
);

-- Insert some sample data
INSERT INTO your_table (name, age) VALUES ('John Doe', 30);
INSERT INTO your_table (name, age) VALUES ('Jane Smith', 25);
INSERT INTO your_table (name, age) VALUES ('Mike Johnson', 35);

DELIMITER //

CREATE PROCEDURE get_user_by_id(IN user_id INT)
BEGIN
    SELECT * FROM your_table WHERE id = user_id;
END //

DELIMITER ;
