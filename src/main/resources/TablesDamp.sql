CREATE SEQUENCE IF NOT EXISTS serial START 101;
CREATE TABLE IF NOT EXISTS public.employee(id serial PRIMARY KEY NOT NULL,firstname text NOT NULL,lastname text NOT NULL,birthdate date NOT NULL )

