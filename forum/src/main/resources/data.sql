INSERT INTO USER(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$10$HctsVLcKFOsFAxWVteVNR.3Qe/6K2rm9A3821CTDlGYYqQaNP/1rm');
INSERT INTO USER(name, email, password) VALUES('Moderador', 'moderador@email.com', '$2a$10$HctsVLcKFOsFAxWVteVNR.3Qe/6K2rm9A3821CTDlGYYqQaNP/1rm');

INSERT INTO PROFILE(id, name) VALUES(1, 'ROLE_STUDENT');
INSERT INTO PROFILE(id, name) VALUES(2, 'ROLE_MODERATOR');

INSERT INTO USER_PROFILES(user_id, profiles_id) VALUES (1, 1);
INSERT INTO USER_PROFILES(user_id, profiles_id) VALUES (2, 2);

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);