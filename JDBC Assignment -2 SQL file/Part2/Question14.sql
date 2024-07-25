create database part2Ques14;
use part2Ques14;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

INSERT INTO students (student_name, age) VALUES ('Alice', 19);
INSERT INTO students (student_name, age) VALUES ('Bob', 21);
INSERT INTO students (student_name, age) VALUES ('Charlie', 17);
INSERT INTO students (student_name, age) VALUES ('David', 22);
INSERT INTO students (student_name, age) VALUES ('Eve', 18);

select *from students;