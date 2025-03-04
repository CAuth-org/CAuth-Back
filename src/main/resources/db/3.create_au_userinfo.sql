CREATE TABLE au_userinfo (
     id VARCHAR(36) PRIMARY KEY COMMENT '用户ID，UUID',
     username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名，唯一',
     password VARCHAR(255) NOT NULL COMMENT '密码（md5(password + salt)）',
     salt VARCHAR(36) NOT NULL COMMENT '密码加密的盐值（UUID）',
     parent VARCHAR(36) NULL COMMENT '上级用户ID，引用au_userinfo(id)',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     CONSTRAINT fk_parent FOREIGN KEY (parent) REFERENCES au_userinfo(id) ON DELETE SET NULL
);