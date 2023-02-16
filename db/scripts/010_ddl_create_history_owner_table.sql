CREATE TABLE IF NOT EXISTS history_owner (
   id SERIAL PRIMARY KEY,
   car_id int not null REFERENCES cars(id),
   driver_id int not null REFERENCES drivers(id),
   startAt TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   endAt TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);