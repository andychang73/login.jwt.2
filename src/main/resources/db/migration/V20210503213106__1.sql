CREATE TABLE IF NOT EXISTS `user`(
    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `username`      VARCHAR(64) NOT NULL COMMENT '用戶名稱',
    `password`      VARCHAR(128) NOT NULL COMMENT '密碼',
    `email`         VARCHAR(128) NOT NULL COMMENT 'email',
    `phone`         VARCHAR(16) NOT NULL COMMENT '電話',
    `is_activated`  TINYINT DEFAULT 0 COMMENT '帳號是否已驗證',
    `status`        INT DEFAULT 1 COMMENT '帳號狀態 0 = 凍結/ 1 = 正常',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;