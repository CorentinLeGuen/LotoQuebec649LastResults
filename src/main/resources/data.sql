DROP TABLE IF EXISTS t_tirages;

CREATE TABLE t_tirages (
    id IDENTITY NOT NULL,
    date_ajout TIMESTAMP WITH TIME ZONE,
    date_tirage TIMESTAMP WITH TIME ZONE,
    numero_1 TINYINT NOT NULL,
    numero_2 TINYINT NOT NULL,
    numero_3 TINYINT NOT NULL,
    numero_4 TINYINT NOT NULL,
    numero_5 TINYINT NOT NULL,
    numero_6 TINYINT NOT NULL,
    numero_complementaire TINYINT NOT NULL
);

