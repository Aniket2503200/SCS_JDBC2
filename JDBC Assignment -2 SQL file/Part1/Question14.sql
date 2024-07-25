CREATE DATABASE IF NOT EXISTS part1Ques14;

USE part1Ques14;

CREATE TABLE IF NOT EXISTS transaction_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    amount INT
);

-- Insert some sample data
INSERT INTO transaction_table (name, amount) VALUES ('John Doe', 500);
INSERT INTO transaction_table (name, amount) VALUES ('Jane Doe', 700);

select *from  transaction_table;