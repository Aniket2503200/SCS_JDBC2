create database part2Ques12;
use part2Ques12;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

INSERT INTO employees (name, salary) VALUES ('John Doe', 50000.00);
INSERT INTO employees (name, salary) VALUES ('Jane Smith', 60000.00);
INSERT INTO employees (name, salary) VALUES ('Michael Johnson', 75000.00);
INSERT INTO employees (name, salary) VALUES ('Emily Davis', 65000.00);
INSERT INTO employees (name, salary) VALUES ('William Brown', 55000.00);

select *from employees;