drop table if exists Person cascade;

create table if not exists Person (
  id int generated by default as identity primary key,
  full_name varchar not null unique,
  year int not null
);

drop table if exists Book cascade;

create table if not exists Book (
  id int generated by default as identity primary key,
  name varchar not null,
  author varchar not null,
  year int not null,
  person_id int references person(id) on delete set null
);