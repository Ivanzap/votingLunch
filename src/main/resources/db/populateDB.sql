TRUNCATE SCHEMA PUBLIC AND COMMIT;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User 1', 'user@yandex.ru', 'password'),
       ('User 2', 'user2@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002),
       ('USER', 100002);

INSERT INTO RESTAURANTS (USER_ID, NAME)
VALUES (100002,'Victoria'),
       (100002,'White Eagle'),
       (100002,'Black and Peace'),
       (100002,'MCDonald''s');

INSERT INTO MEALS (USER_ID, RESTAURANT_ID, NAME, PRICE, DATE_TIME)
VALUES (100002, 100003, 'Sup Victoria 1', 70, '2021-07-01 09:00:00'),
       (100002, 100003, 'Vtoroe Victoria 1', 150, '2021-07-01 09:00:00'),
       (100002, 100003, 'Salat Victoria 1', 50, '2021-07-01 09:00:00'),
       (100002, 100003, 'Sup Victoria 2', 50, '2021-07-02 09:20:00'),
       (100002, 100003, 'Vtoroe Victoria 2', 120, '2021-07-02 09:20:00'),
       (100002, 100003, 'Salat Victoria 2', 30, '2021-07-02 09:20:00'),
       (100002, 100004, 'Sup White Eagle 1', 60, '2021-07-01 09:00:00'),
       (100002, 100004, 'Vtoroe White Eagle 1', 100, '2021-07-01 09:00:00'),
       (100002, 100004, 'Salat White Eagle 1', 50, '2021-07-01 09:00:00'),
       (100002, 100004, 'Sup White Eagle 2', 50, '2021-07-02 09:20:00'),
       (100002, 100004, 'Vtoroe White Eagle 2', 120, '2021-07-02 09:20:00'),
       (100002, 100004, 'Salat White Eagle 2', 30, '2021-07-02 09:20:00'),
       (100002, 100005, 'Sup Black and Peace 1', 50, '2021-07-01 09:10:00'),
       (100002, 100005, 'Vtoroe Black and Peace 1', 120, '2021-07-01 09:10:00'),
       (100002, 100005, 'Salat Black and Peace 1', 30, '2021-07-01 09:10:00'),
       (100002, 100005, 'Sup Black and Peace 2', 50, '2021-07-02 09:25:00'),
       (100002, 100005, 'Vtoroe Black and Peace 2', 120, '2021-07-02 09:25:00'),
       (100002, 100005, 'Salat Black and Peace 2', 30, '2021-07-02 09:25:00'),
       (100002, 100006, 'Burger MCDonald''s 1', 120, '2021-07-01 09:10:00'),
       (100002, 100006, 'Potato MCDonald''s 1', 50, '2021-07-01 09:10:00'),
       (100002, 100006, 'Cola MCDonald''s 1', 30, '2021-07-01 09:10:00'),
       (100002, 100006, 'Burger MCDonald''s 2', 120, '2021-07-02 09:15:00'),
       (100002, 100006, 'Potato MCDonald''s 2', 50, '2021-09-05 09:15:00'),
       (100002, 100006, 'Cola MCDonald''s 2', 30, '2021-09-05 09:15:00');

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, DATE_TIME)
VALUES (100000, 100004, '2021-07-02 11:15:24'),
       (100001, 100004, '2021-07-02 11:24:12'),
       (100002, 100005, '2021-07-02 10:39:44');