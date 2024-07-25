create database part2Ques7;

use part2Ques7;

CREATE TABLE departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);

INSERT INTO departments (department_name, location) VALUES ('HR', 'New York');
INSERT INTO departments (department_name, location) VALUES ('Finance', 'London');
INSERT INTO departments (department_name, location) VALUES ('Engineering', 'San Francisco');
INSERT INTO departments (department_name, location) VALUES ('Marketing', 'Chicago');
INSERT INTO departments (department_name, location) VALUES ('Sales', 'Tokyo');

select *from departments;