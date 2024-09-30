INSERT INTO app.role (name)
VALUES ('ADMIN'),
('USER');

INSERT INTO app."user" (id, full_name, login, "password",date_of_birth,updated_at, created_at, role_id)
VALUES(gen_random_uuid (), 'admin', 'admin', 'admin_admin', '1900-01-01', now(), now(),
(SELECT id FROM app.role WHERE name = 'ADMIN';));