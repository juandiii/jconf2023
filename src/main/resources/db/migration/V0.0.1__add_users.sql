CREATE TABLE "users"
(
    id          uuid,
    first_name  character varying(255) NOT NULL,
    middle_name character varying(255),
    last_name   character varying(255) NOT NULL,
    email       character varying(255) NOT NULL,
    username    character varying(255) NOT NULL,
    password    text                   NOT NULL,
    dob         date                   NOT NULL,
    PRIMARY KEY (id)
);