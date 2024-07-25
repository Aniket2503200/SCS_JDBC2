-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques4;

-- Use the created database
USE part1Ques4;

-- Create the table
CREATE TABLE IF NOT EXISTS your_table (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    PRIMARY KEY (id)
);

-- Insert sample data into the table
INSERT INTO your_table (name, age) VALUES ('Omkar', 30);
INSERT INTO your_table (name, age) VALUES ('Babu', 25);
INSERT INTO your_table (name, age) VALUES ('Charlie', 35);

select *from your_table;