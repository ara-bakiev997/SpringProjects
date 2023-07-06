drop table if exists Person;

create table if not exists Person (
  id int generated by default as identity primary key,
  full_name varchar not null unique,
  year int not null
);

insert into Person (full_name, year) values ('FIO', 1997);
insert into Person (full_name, year) values ('BAI', 1997);
insert into Person (full_name, year) values ('ARA', 1997);

drop table if exists Book;

create table if not exists Book (
  id int generated by default as identity primary key,
  name varchar not null,
  author varchar not null,
  year int not null
);


