CREATE TABLE app.users(
id UUID,
full_name CHARACTER VARYING,
login CHARACTER VARYING NOT NULL,
password CHARACTER VARYING NOT NULL,
date_of_birth DATE NOT NULL,
updated_at TIMESTAMP NOT NULL,
created_at TIMESTAMP NOT NULL,
role CHARACTER VARYING,
CONSTRAINT users_pk PRIMARY KEY (id),
CONSTRAINT users_login_unq UNIQUE(login)
);

INSERT INTO app.users (id, full_name, login, password, date_of_birth, updated_at, created_at, role)
SELECT u.id, u.full_name, u.login, u.password, u.date_of_birth, u.updated_at, u.created_at, r.name
FROM app."user" u
JOIN app.role r ON u.role_id = r.id;

ALTER TABLE app.users
	ALTER COLUMN role SET NOT NULL;

ALTER TABLE app."user"
	DROP CONSTRAINT user_role_fk;

ALTER TABLE app."user"
	DROP CONSTRAINT login_unique;

DROP TABLE app."user";
DROP TABLE app.role;