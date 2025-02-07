create table if not exists horoscopo
(
    animal       varchar(10) not null,
    fecha_inicio date not null,
    fecha_fin    date not null
);