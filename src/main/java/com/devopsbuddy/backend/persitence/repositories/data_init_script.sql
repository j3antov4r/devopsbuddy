INSERT INTO plan (ID, NAME) VALUES (1, 'Basic');
INSERT INTO plan (ID, NAME) VALUES (2, 'Pro');

INSERT INTO role (id, name) VALUES (1, 'ROLE_BASIC');

INSERT INTO role (id, name) VALUES (2, 'ROLE_PRO');

INSERT INTO role (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO user (ID, USERNAME, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, DESCRIPTION, PASSWORD, ENABLED, COUNTRY , PLAN_ID)
VALUES
(1, 'user', 'jean@123.com', 'Jean','Tovar', '87654566', 'A basic user', '1234', true, 'VE', 1);

INSERT INTO user_role (ID, ROLE_ID, USER_ID ) values (1, 1, 1);