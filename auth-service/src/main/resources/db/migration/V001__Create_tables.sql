CREATE TABLE t_users (
    id      SERIAL PRIMARY KEY,
    email   VARCHAR(128) UNIQUE NOT NULL,
    status  VARCHAR(16)
);

CREATE TABLE t_codes (
    id          SERIAL PRIMARY KEY,
    user_id     SERIAL NOT NULL,
    code        INT    NOT NULL,
    expires_in  TIMESTAMPTZ,
    FOREIGN KEY (user_id) REFERENCES t_users (id)
)