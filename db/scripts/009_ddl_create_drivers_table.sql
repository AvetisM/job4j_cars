CREATE TABLE IF NOT EXISTS DRIVERS (
   id SERIAL PRIMARY KEY,
   name TEXT,
   user_id int not null REFERENCES auto_user(id)
);