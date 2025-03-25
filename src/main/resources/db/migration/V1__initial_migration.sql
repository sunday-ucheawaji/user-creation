create table users
(
    id       bigint auto_increment
        primary key,
    first_name     varchar(255) not null,
    last_name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);