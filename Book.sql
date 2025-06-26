CREATE DATABASE  book_api_jwt_db;
USE book_api_jwt_db;


CREATE TABLE book (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INT NOT NULL
);


CREATE TABLE app_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO app_user (username, password) VALUES
('john_doe', 'password123'),
('jane_smith', 'securepass456'),
('alice_walker', 'mypassword789');

INSERT INTO book (isbn, title, author, publication_year)
VALUES ('9780134685991', 'Effective Java', 'Joshua Bloch', 2018);

INSERT INTO book (isbn, title, author, publication_year)
VALUES ('9780596009205', 'Head First Java', 'Kathy Sierra', 2005);

INSERT INTO book (isbn, title, author, publication_year)
VALUES ('9781617294945', 'Spring in Action', 'Craig Walls', 2018);

UPDATE app_user SET password = '$2a$10$n0XDLtZk02AaUxfiHguQfOOiYMSB5lEMl882/4GckCxYCKF.pffG.' 
WHERE username = 'john_doe';

UPDATE app_user SET password = '$2a$10$f4O78lJGM1S33Mcs2KRIWeKP4N5f.R3ZYnxBmyK.1C7IiycpSzRSq' 
WHERE username = 'jane_smith';

UPDATE app_user SET password = '$2a$10$RL2RplqRCmwU7OcoUJDFY.e/0OjFHH68MnH3QKq1ZBjxjVWe4M/5S' 
WHERE username = 'alice_walker';

