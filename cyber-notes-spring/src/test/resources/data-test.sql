merge into ACCOUNT (id, username, password) key(ID)
values (0, 'admin', 'admin');

merge into ACCOUNT (id, username, password) key(ID)
values (1, 'test', 'test');

merge into ACCOUNT (id, username, password) key(ID)
values (2, 'aaa', 'aaa');




merge into NOTE (id, title, text, user_id) key(ID)
values (0, 'nazov1', 'text1', 0);

merge into NOTE (id, title, text, user_id) key(ID)
values (1, 'testaaa', 'test', 0);

merge into NOTE (id, title, text, user_id) key(ID)
values (2, 'bbb', 'bbb', 1);