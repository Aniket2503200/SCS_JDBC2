create database part2Ques18;
use part2Ques18;

CREATE TABLE employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10, 2)
);

-- Insert initial data
INSERT INTO employees (first_name, last_name, email, department, salary) VALUES 
('Alice', 'Johnson', 'alice.johnson@example.com', 'HR', 60000),
('Bob', 'Smith', 'bob.smith@example.com', 'IT', 55000),
('Charlie', 'Brown', 'charlie.brown@example.com', 'Finance', 70000);

select *from employees;