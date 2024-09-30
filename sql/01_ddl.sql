CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.role(
id UUID,
name CHARACTER VARYING(30) NOT NULL,
CONSTRAINT role_pk PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.role
    OWNER to postgres;

CREATE TABLE app."user"(
id UUID,
full_name CHARACTER VARYING,
login CHARACTER VARYING NOT NULL,
password CHARACTER VARYING NOT NULL,
date_of_birth DATE NOT NULL,
updated_at TIMESTAMP NOT NULL,
created_at TIMESTAMP NOT NULL,
role_id UUID,
CONSTRAINT user_pk PRIMARY KEY (id),
CONSTRAINT user_role_fk  FOREIGN KEY (role_id) REFERENCES app.role(id),
CONSTRAINT login_unique  UNIQUE(login)
);

ALTER TABLE IF EXISTS app."user"
    OWNER to postgres;





