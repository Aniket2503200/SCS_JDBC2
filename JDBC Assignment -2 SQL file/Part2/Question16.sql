create database part2Ques16;

use part2Ques16;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

INSERT INTO employees (first_name, last_name, email, department, salary) VALUES
('John', 'Doe', 'john.doe@example.com', 'HR', 50000.00),
('Jane', 'Smith', 'jane.smith@example.com', 'Finance', 60000.00),
('Emily', 'Jones', 'emily.jones@example.com', 'IT', 55000.00);

select *from employees;