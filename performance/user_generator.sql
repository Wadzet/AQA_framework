INSERT INTO mantis_user_table (username, realname, email, password, enabled, protected, access_level, cookie_string)
VALUES ('dev', 'John Doe', 'dev@gmail.com', MD5('password'), 1, 0, 35, UUID());
