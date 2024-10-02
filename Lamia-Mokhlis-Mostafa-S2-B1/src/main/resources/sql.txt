create database projectmanagement

-- Table for 'Team'
CREATE TABLE Team (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Table for 'Member'
CREATE TABLE Member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role ENUM('project_manager', 'developer', 'designer') NOT NULL,
    team_id INT REFERENCES Team(id) on delete cascade
);

-- Table for 'Project'
CREATE TABLE Project (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    startDate DATE NOT NULL,
    endDate DATE,
    status ENUM('in_preparation', 'in_progress', 'on_hold', 'completed', 'canceled') NOT NULL,
    team_id INT REFERENCES Team(id) ON DELETE SET NULL
);

-- Table for 'Task'
CREATE TABLE Task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    priority ENUM('low', 'medium', 'high') NOT NULL,
    status ENUM('to_do', 'in_progress', 'completed') NOT NULL,
    creationDate DATE NOT NULL,
    dueDate DATE NOT NULL,
    project_id INT REFERENCES Project(id) ON DELETE CASCADE,
    member_id INT REFERENCES Member(id) ON DELETE SET NULL
);

-- Indexes
CREATE INDEX idx_project_name ON Project(name);
CREATE INDEX idx_task_project ON Task(project_id);
CREATE INDEX idx_member_email ON Member(email);

-- create user
CREATE USER 'user_app'@'localhost' IDENTIFIED BY '12345';

-- Grant Privileges
GRANT SELECT, INSERT, UPDATE, DELETE ON projectmanagement.* TO 'user_app'@'localhost';

-- Apply the changes
FLUSH PRIVILEGES;