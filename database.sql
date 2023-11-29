CREATE DATABASE task_hub;

USE task_hub;

CREATE TABLE users
(
    username         VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(100) NOT NULL,
    token            VARCHAR(100),
    token_expired_at BIGINT,
    PRIMARY KEY (username),
    UNIQUE (token)
) ENGINE InnoDB;

CREATE TABLE tasks
(
    id           VARCHAR(100) NOT NULL,
    username     VARCHAR(100) NOT NULL,
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(100) NOT NULL,
    status       VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_tasks (username) REFERENCES users (username)
) ENGINE InnoDB;
