DELETE FROM users;

insert into users(id, email, password, name, role, created_at) values
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Administrator', 'ROLE_ADMIN', CURRENT_TIMESTAMP),
(2, 'siva@gmail.com', '$2a$10$rjn41FlydyGBKN0kSZRCwOS.fTbBBhjtgDsE0eLuNmG9TEpZyI/k6', 'Siva','ROLE_USER', CURRENT_TIMESTAMP);
