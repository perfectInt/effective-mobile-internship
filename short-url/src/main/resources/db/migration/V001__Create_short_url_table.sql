CREATE TABLE t_urls (
    id SERIAL PRIMARY KEY,
    original_url VARCHAR(128) NOT NULL,
    short_url VARCHAR(32) NOT NULL UNIQUE
)