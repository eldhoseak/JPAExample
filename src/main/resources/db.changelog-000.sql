--liquibase formatted sql

--changeset cu39:1
create table employee (
    id              int                 primary key  auto_increment,
    name            varchar(255)        not null,
    created_at      datetime            not null  default now(),
    updated_at      datetime            not null  default now(),
    deleted_at      datetime
);
--rollback drop table employee;
