create database part2Ques9;

use part2Ques9;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

INSERT INTO employees (first_name, last_name, email, department, salary) VALUES ('John', 'Doe', 'john.doe@example.com', 'HR', 50000.00);
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'Finance', 60000.00);
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES ('Michael', 'Johnson', 'michael.johnson@example.com', 'Engineering', 75000.00);
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES ('Emily', 'Davis', 'emily.davis@example.com', 'Marketing', 65000.00);
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES ('William', 'Brown', 'william.brown@example.com', 'Sales', 55000.00);

select *from employees;