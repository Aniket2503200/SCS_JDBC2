create database part2Ques13;

use part2Ques13;

CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL
);

INSERT INTO departments (department_name) VALUES ('Human Resources');
INSERT INTO departments (department_name) VALUES ('Finance');
INSERT INTO departments (department_name) VALUES ('Marketing');
INSERT INTO departments (department_name) VALUES ('IT');
INSERT INTO departments (department_name) VALUES ('Sales');


select *from departments;