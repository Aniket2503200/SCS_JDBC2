create database part2Ques8;

use part2Ques8;

CREATE TABLE products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO products (product_name, price) VALUES ('Laptop', 999.99);
INSERT INTO products (product_name, price) VALUES ('Smartphone', 599.99);
INSERT INTO products (product_name, price) VALUES ('Tablet', 299.99);
INSERT INTO products (product_name, price) VALUES ('Headphones', 149.99);
INSERT INTO products (product_name, price) VALUES ('Smartwatch', 199.99);


select *from products;