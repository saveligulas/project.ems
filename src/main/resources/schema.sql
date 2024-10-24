CREATE TABLE IF NOT EXISTS "user" (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS "user_roles" (
    user_id BIGSERIAL NOT NULL,
    role_ordinal INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);