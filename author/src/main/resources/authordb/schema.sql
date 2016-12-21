drop table t_author if exists;

create table t_author (
    ID bigint identity primary key
  , NAME varchar(124) not null
);