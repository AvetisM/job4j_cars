CREATE TABLE IF NOT EXISTS participates (
   id serial PRIMARY KEY,
   auto_user_id int not null REFERENCES auto_user(id),
   post_id int not null REFERENCES auto_post(id)
);