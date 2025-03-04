INSERT INTO au_role (id, role_name, description)
VALUES
    (UUID(), 'admin', '管理员角色'),
    (UUID(), 'user', '普通用户角色')
    ON DUPLICATE KEY UPDATE role_name = VALUES(role_name), description = VALUES(description);