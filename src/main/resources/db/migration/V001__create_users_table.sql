create sequence user_id_seq start with 100 increment by 50;

create table users
(
    id         bigint    not null default nextval('user_id_seq'),
    email      text      not null,
    password   text      not null,
    name       text      not null,
    role       text      not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,
    version    bigint       not null default 0,
    primary key (id),
    constraint user_email_unique unique (email)
);

insert into users(id, email, password, name, role, created_at) values
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Administrator', 'ROLE_ADMIN', CURRENT_TIMESTAMP),
(2, 'siva@gmail.com', '$2a$10$rjn41FlydyGBKN0kSZRCwOS.fTbBBhjtgDsE0eLuNmG9TEpZyI/k6', 'Siva','ROLE_USER', CURRENT_TIMESTAMP);
