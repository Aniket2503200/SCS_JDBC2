create database part2Ques17;
use part2Ques17;

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO products (product_name, price) VALUES ('Laptop', 1000.00);
INSERT INTO products (product_name, price) VALUES ('Smartphone', 500.00);
INSERT INTO products (product_name, price) VALUES ('Tablet', 300.00);


select *from products;