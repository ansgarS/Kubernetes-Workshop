CREATE DATABASE patientdb;

\connect patientdb;

-- Use this example Table:
CREATE TABLE movie (
       id          varchar(255) PRIMARY KEY,
       imdb_id     varchar(255) NOT NULL,
       title       varchar(255),
       type        varchar(255),
       price       float NOT NULL,
       created_by  varchar(255) NOT NULL,
       created_on  TIMESTAMPTZ NOT NULL,

       CONSTRAINT unique_id_by_author UNIQUE (imdb_id, created_by)
);