-- Create teams table
CREATE TABLE IF NOT EXISTS Teams (
        id int AUTO_INCREMENT NOT NULL
    ,   name VARCHAR(50)
    ,   league VARCHAR(50)
    ,   country VARCHAR(50)
    ,   CONSTRAINT pk_teams_id PRIMARY KEY (id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS Users (
        id UUID default random_uuid()
    ,   username VARCHAR(255) NOT NULL
    ,   password VARCHAR(255) NOT NULL
    ,   role VARCHAR(50) NOT NULL
    ,   CONSTRAINT pk_user_id PRIMARY KEY (id)
);

