-- Create the database
CREATE DATABASE part1Ques1;

-- Use the created database
USE part1Ques1;

-- Create the table
CREATE TABLE your_table (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    PRIMARY KEY (id)
);

-- Insert sample data into the table
INSERT INTO your_table (name, age) VALUES ('Aniket', 30);
INSERT INTO your_table (name, age) VALUES ('Varsha', 25);
INSERT INTO your_table (name, age) VALUES ('Charlie', 35);
