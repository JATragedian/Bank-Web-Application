DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cards CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;

CREATE TABLE IF NOT EXISTS roles (
id serial PRIMARY KEY,
role varchar not null);

CREATE TABLE IF NOT EXISTS users (
user_id serial PRIMARY KEY,
first_name varchar not null,
last_name varchar not null,
email varchar not null UNIQUE,
password varchar not null,
role_id int not null,
FOREIGN KEY (role_id) references roles(id) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE IF NOT EXISTS accounts (
account_id varchar PRIMARY KEY CHECK (LENGTH(account_id) = 20),
balance numeric not null,
user_id serial not null,
isBlocked boolean DEFAULT false,
FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE IF NOT EXISTS cards (
card_number varchar PRIMARY KEY CHECK (LENGTH(card_number) = 16),
account_id varchar CHECK (LENGTH(account_id) = 20),
FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE IF NOT EXISTS transactions (
transaction_id serial PRIMARY KEY,
card_number varchar not null CHECK (LENGTH(card_number) = 16),
date date not null,
amount numeric not null,
target_account varchar not null CHECK (LENGTH(target_account) = 20),
FOREIGN KEY (card_number) REFERENCES cards(card_number) ON DELETE CASCADE ON UPDATE CASCADE );
