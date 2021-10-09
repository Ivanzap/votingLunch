TRUNCATE SCHEMA PUBLIC AND COMMIT;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User 1', 'user@yandex.ru', '{noop}password'),
       ('User 2', 'user2@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('USER', 100002);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Victoria'),
       ('White Eagle'),
       ('Black and Peace'),
       ('MCDonald''s');

INSERT INTO DISHES (RESTAURANT_ID, NAME, PRICE, DATE)
VALUES (100003, 'Sup Victoria 1', 70, CURDATE() - '1' DAY),
       (100003, 'Vtoroe Victoria 1', 150, CURDATE() - '1' DAY ),
       (100003, 'Salat Victoria 1', 50, CURDATE() - '1' DAY ),
       (100003, 'Sup Victoria 2', 50, CURDATE()),
       (100003, 'Vtoroe Victoria 2', 120, CURDATE()),
       (100003, 'Salat Victoria 2', 30, CURDATE()),
       (100004, 'Sup White Eagle 1', 60, CURDATE() - '1' DAY ),
       (100004, 'Vtoroe White Eagle 1', 100, CURDATE() - '1' DAY ),
       (100004, 'Salat White Eagle 1', 50, CURDATE() - '1' DAY ),
       (100004, 'Sup White Eagle 2', 50, CURDATE()),
       (100004, 'Vtoroe White Eagle 2', 120, CURDATE()),
       (100004, 'Salat White Eagle 2', 30, CURDATE()),
       (100005, 'Sup Black and Peace 1', 50,  '2021-10-03'),
       (100005, 'Vtoroe Black and Peace 1', 120, '2021-10-03'),
       (100005, 'Salat Black and Peace 1', 30, '2021-10-03'),
       (100005, 'Sup Black and Peace 2', 50, CURDATE()),
       (100005, 'Vtoroe Black and Peace 2', 120, CURDATE()),
       (100005, 'Salat Black and Peace 2', 30, CURDATE()),
       (100006, 'Burger MCDonald''s 1', 120, CURDATE() - '1' DAY ),
       (100006, 'Potato MCDonald''s 1', 50, CURDATE() - '1' DAY ),
       (100006, 'Cola MCDonald''s 1', 30, CURDATE() - '1' DAY ),
       (100006, 'Burger MCDonald''s 2', 120, CURDATE()),
       (100006, 'Potato MCDonald''s 2', 50, CURDATE()),
       (100006, 'Cola MCDonald''s 2', 30, CURDATE());

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, DATE, TIME)
VALUES (100000, 100004, TODAY, '09:00:00'),
       (100001, 100004, TODAY - '1' DAY, '08:00:00'),
       (100002, 100005, TODAY, '09:30:00');