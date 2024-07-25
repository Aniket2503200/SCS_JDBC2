create database part2Ques4;

use part2Ques4;

CREATE TABLE your_table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

INSERT INTO your_table (name, age) VALUES ('Alice', 30);
INSERT INTO your_table (name, age) VALUES ('Bob', 25);
INSERT INTO your_table (name, age) VALUES ('Charlie', 35);
INSERT INTO your_table (name, age) VALUES ('David', 40);
INSERT INTO your_table (name, age) VALUES ('Eve', 22);

select *from your_table;