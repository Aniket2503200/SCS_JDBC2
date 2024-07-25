CREATE DATABASE IF NOT EXISTS part1Ques13;

USE part1Ques13;

CREATE TABLE IF NOT EXISTS transaction_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    amount INT
);

-- Insert some sample data
INSERT INTO transaction_table (name, amount) VALUES ('John Doe', 500);

select *from transaction_table;