drop table T_BOOK if exists;

create table T_BOOK (
    ID bigint identity primary key
  , TITLE varchar(124) not null
  , AUTHOR bigint not null
  , ISBN varchar(13) not null
);