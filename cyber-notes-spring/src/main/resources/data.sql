merge into ACCOUNT key(ID)
values (1, 'admin', 'admin');

merge into NOTE key(ID)
values (1, 'nazov1', 'text1', 1);

ALTER TABLE ACCOUNT ALTER COLUMN id RESTART WITH 2