CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.users(
id UUID,
full_name CHARACTER VARYING,
login CHARACTER VARYING NOT NULL,
password CHARACTER VARYING NOT NULL,
date_of_birth DATE NOT NULL,
updated_at TIMESTAMP NOT NULL,
created_at TIMESTAMP NOT NULL,
role CHARACTER VARYING NOT NULL,
CONSTRAINT users_pk PRIMARY KEY (id),
CONSTRAINT users_login_unq UNIQUE(login)
);

CREATE TABLE app.messages (
	id bigserial NOT NULL,
	user_from_id uuid NULL,
	user_to_id uuid NULL,
	"text" CHARACTER VARYING NULL,
	delivered_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
	CONSTRAINT messages_pk PRIMARY KEY (id),
	CONSTRAINT messages_users_from_fk FOREIGN KEY (user_from_id) REFERENCES app.users (id),
	CONSTRAINT messages_users_to_fk FOREIGN KEY (user_to_id) REFERENCES app.users (id)
);

CREATE TABLE app.statistics (
	id bigserial NOT NULL,
	counter_name CHARACTER VARYING NULL,
	counter_value bigint,
	CONSTRAINT statistics_pk PRIMARY KEY (id),
	CONSTRAINT statistics_name_unq UNIQUE (counter_name)
);





