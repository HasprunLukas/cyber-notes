CREATE TABLE IF NOT EXISTS ACCOUNT (
                                id LONG AUTO_INCREMENT PRIMARY KEY,
                                username VARCHAR(255) UNIQUE NOT NULL,
                                password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS NOTE (
                                id LONG AUTO_INCREMENT PRIMARY KEY,
                                title VARCHAR(255),
                                text CLOB,
                                user_id LONG NOT NULL,
                                CONSTRAINT fk_user_id FOREIGN KEY (user_id) references ACCOUNT (id)
);