create table if not exists usuarios
(
    id               int auto_increment primary key,
    nombre           varchar(30) not null,
    username         varchar(30) not null,
    email            varchar(30) not null,
    fecha_nacimiento date        null,
    password         varchar(30) not null,
    animal           varchar(30) not null
);

