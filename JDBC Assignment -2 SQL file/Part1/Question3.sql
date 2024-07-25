-- Create the database
CREATE DATABASE IF NOT EXISTS part1Ques3;

-- Use the created database
USE part1Ques3;

-- Create the table
CREATE TABLE IF NOT EXISTS your_table (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    marks INT NOT NULL,
    PRIMARY KEY (id)
);

-- Insert sample data into the table
INSERT INTO your_table (name, age,marks) VALUES ('Aniket', 30,60);
INSERT INTO your_table (name, age,marks) VALUES ('Bob', 25,86);
INSERT INTO your_table (name, age,marks) VALUES ('Varsha', 35,97);

select *from your_table;