DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id           BIGINT NOT NULL IDENTITY (1,1),
    username     VARCHAR(30),
    email        VARCHAR(50),
    phone_number VARCHAR(10),
    PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT CHK_PHONE_NUMBER
        CHECK (REGEXP_LIKE(phone_number, '^0\d{9}|\d{10}$'));

CREATE TABLE accounts
(
    id      BIGINT NOT NULL,
    balance DOUBLE,
    CONSTRAINT balance CHECK (balance >= 0.0),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES users (id)
);

ALTER TABLE accounts
    ALTER COLUMN balance SET DEFAULT '0.0';

/**
  Insert data into users table
 */
INSERT INTO users(username, email, phone_number)
VALUES ('Valckata333', 'valkata999999@gmail.com', '0883640808');

/**
 * Transaction should not be made due to unique constraint on username
 */
INSERT INTO users(username, email, phone_number)
VALUES ('Valckata333', 'somemail@gmail.com', '0800000000');

INSERT INTO users(username, email, phone_number)
VALUES ('Todor', 'another.email@abv.bg', '0898989898');

INSERT INTO users(username, email, phone_number)
VALUES ('Another one', 'mitkata@abc.abc', '0987654321');

INSERT INTO users(username, email, phone_number)
VALUES ( 'No account user', 'invalidmail@abv.bg', '0123456789' );

/**
  Insert data into accounts table
 */

INSERT INTO accounts(id, balance)
VALUES ('1', '15');
/**
  There is no such key in the user database
 */
INSERT INTO accounts(id, balance)
VALUES ('2', '0.0');

INSERT INTO accounts(id)
VALUES ('3');

INSERT INTO accounts(id, balance)
VALUES ( '4', '32.6' );

/**

 */
Select username, a.id, a.balance
FROM users INNER JOIN accounts a on users.id = a.id
Where balance > 1000;

/**
  Queries which make a copy user information about a person with
  username 'Todor'
 */
insert into users (username, email, phone_number)
select username, email, phone_number
from users
where username = 'Todor';

INSERT INTO accounts(id, balance)
SELECT users.id, balance
From users LEFT JOIN accounts a on users.id = a.id
Where username = 'Todor' AND a.id IS NULL;

UPDATE accounts
SET balance = (SELECT balance
               FROM users INNER JOIN accounts a on users.id = a.id
               WHERE a.balance IS NOT NULL AND username = 'Todor')
WHERE balance IS NULL AND id = (Select id FROM USERS WHERE username = 'Todor');