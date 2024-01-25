-- Create database for Auth-service
CREATE DATABASE IF NOT EXISTS auth_service_db;

-- Use the database
USE auth_service_db;

-- Create user table
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

-- Insert sample user
INSERT INTO user (username, password, email, roles) 
VALUES ('sampleUser', 'hashedPassword', 'sample@example.com', 'ROLE_USER');
