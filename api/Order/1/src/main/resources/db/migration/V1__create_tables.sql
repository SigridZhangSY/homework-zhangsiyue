CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  password varchar(255)
);

insert into users (email, password) values ("admin@example.com", "PASS");