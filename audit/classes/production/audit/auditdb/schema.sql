drop table T_AUDIT if exists;

create table T_AUDIT (
    ID bigint identity primary key
  , EVENT varchar(124) not null
  , CREATED_AT datetime default NOW() not null
);