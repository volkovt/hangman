// SEED

INSERT INTO tb_word (name) VALUES ('DELL');
INSERT INTO tb_word (name) VALUES ('DELIVER');
INSERT INTO tb_word (name) VALUES ('TECHNOLOGY');
INSERT INTO tb_word (name) VALUES ('CUSTOMER');
INSERT INTO tb_word (name) VALUES ('CLOUD');
INSERT INTO tb_word (name) VALUES ('COMPUTER');
INSERT INTO tb_word (name) VALUES ('SERVER');
INSERT INTO tb_word (name) VALUES ('ADVANCED');
INSERT INTO tb_word (name) VALUES ('STORAGE');
INSERT INTO tb_word (name) VALUES ('SOLLUTIONS');
INSERT INTO tb_word (name) VALUES ('COMMITMENT');
INSERT INTO tb_word (name) VALUES ('DIVERSITY');
INSERT INTO tb_word (name) VALUES ('ENGAGEMENT');
INSERT INTO tb_word (name) VALUES ('COMMUNITY');
INSERT INTO tb_word (name) VALUES ('MILESTONE');

INSERT INTO tb_user (name, email, password) VALUES ('admin', 'admin@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
