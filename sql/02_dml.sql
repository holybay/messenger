INSERT INTO app.users (id, full_name, login, "password", date_of_birth,updated_at, created_at, role)
VALUES(gen_random_uuid (), 'admin', 'admin', 'admin_admin', '1900-01-01', now(), now(), 'ADMIN');