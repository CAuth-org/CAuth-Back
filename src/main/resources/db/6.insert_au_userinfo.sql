-- 插入管理员用户
INSERT INTO au_userinfo (id, username, password, salt, parent, role_id)
VALUES
    (UUID(), 'admin', MD5(CONCAT('admin', UUID())), UUID(), NULL, 'adde9788-f8d2-11ef-b757-001a7dda710f'),
    (UUID(), 'user', MD5(CONCAT('user', UUID())), UUID(), NULL, 'addea763-f8d2-11ef-b757-001a7dda710f');