CREATE DATABASE IF NOT EXISTS part1Ques15;

USE part1Ques15;

CREATE TABLE IF NOT EXISTS batch_update_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    amount INT
);

-- Insert some sample data
INSERT INTO batch_update_table (name, amount) VALUES ('John Doe', 500);
INSERT INTO batch_update_table (name, amount) VALUES ('Jane Doe', 700);
INSERT INTO batch_update_table (name, amount) VALUES ('Sam Smith', 800);

select *from batch_update_table;