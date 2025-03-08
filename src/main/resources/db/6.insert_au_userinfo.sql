-- 插入管理员用户
INSERT INTO au_userinfo (id, username, password, salt, parent, role_id)
VALUES
    (UUID(), 'admin', MD5(CONCAT('admin', UUID())), UUID(), NULL, (select id from au_role where role_name = 'admin')),
    (UUID(), 'user', MD5(CONCAT('user', UUID())), UUID(), NULL, (select id from au_role where role_name = 'user'))