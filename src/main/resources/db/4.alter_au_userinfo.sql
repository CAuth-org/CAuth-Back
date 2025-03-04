ALTER TABLE au_userinfo
    ADD COLUMN role_id VARCHAR(36) NOT NULL COMMENT '用户角色，关联au_role表';


