CREATE TABLE samples (
    id SERIAL PRIMARY KEY ,
    text VARCHAR(255)
);
CREATE TABLE users (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL ,
    lastName VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);
