insert into ACCOUNT(id, username, password) values
                                             (1, 'admin', 'admin'),
                                             (2, 'test', 'test'),
                                             (3, 'aaa', 'aaa');

insert into NOTE(id, title, text, user_id) values
                                                (1, 'nazov1', 'text1', 1),
                                                (2, 'testaaa', 'test', 1),
                                                (3, 'bbb', 'bbb', 2);

ALTER TABLE ACCOUNT ALTER COLUMN id RESTART WITH 4