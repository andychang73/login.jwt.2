CREATE TABLE IF NOT EXISTS verification_token(
    `id`            VARCHAR(64) NOT NULL COMMENT 'uuid主鍵',
    `username`      VARCHAR(128) NOT NULL COMMENT '用戶名稱',
    `password`      VARCHAR(128) NOT NULL COMMENT '密碼',
    `email`         VARCHAR(128) NOT NULL COMMENT 'Email',
    `phone`         VARCHAR(16) NOT NULL COMMENT '電話號碼',
    `expiration`    DATETIME NOT NULL COMMENT '有效期限',
    `create_time`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;