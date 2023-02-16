CREATE TABLE IF NOT EXISTS CARS (
   id SERIAL PRIMARY KEY,
   name TEXT,
   engine_id int not null REFERENCES engines(id)
);