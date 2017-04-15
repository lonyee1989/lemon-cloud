-- user table
create table s_user (
  id BIGINT PRIMARY KEY,
  mobile VARCHAR(20) UNIQUE
);