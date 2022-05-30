CREATE SCHEMA IF NOT EXISTS jdbc
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS jdbc.students
(
    id bigserial NOT NULL,
    name character varying(10) COLLATE pg_catalog."default",
    age integer,
    score double precision,
    olympic_gamer boolean,
    CONSTRAINT students_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS jdbc.students
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS jdbc.groups
(
    id bigserial NOT NULL,
    name character varying(25) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id),
    CONSTRAINT unic_name UNIQUE (name)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS jdbc.groups
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS jdbc.cross_table
(
    id_group integer,
    id_student integer,
    CONSTRAINT students_unique UNIQUE (id_student),
    CONSTRAINT id_groupfk FOREIGN KEY (id_group)
    REFERENCES jdbc.groups (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT students_idfk FOREIGN KEY (id_student)
    REFERENCES jdbc.students (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS jdbc.cross_table
    OWNER to postgres;

