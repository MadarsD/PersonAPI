CREATE TABLE persons
(
    person_id  INTEGER PRIMARY KEY UNIQUE,
    name      VARCHAR(100) NOT NULL,
    surname   VARCHAR(100) NOT NULL,
    phone     VARCHAR(100) NOT NULL,
    email     VARCHAR(300) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;