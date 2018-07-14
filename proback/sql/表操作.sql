
禁用的用户名名  中间以 , 隔开
CREATE TABLE `tb_name_forbidden` (
  `name` VARCHAR(255) DEFAULT NULL COMMENT '禁用用户名'
) ENGINE=INNODB DEFAULT CHARSET=utf8

会员表


管理员表

CREATE TABLE `tb_admin` (
  `id` INT(10) PRIMARY KEY AUTO_INCREMENT,
  `username` CHAR(20) NOT NULL COMMENT '用户名',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '用户邮箱',
  `mobile` CHAR(11) DEFAULT NULL COMMENT '用户手机',
  `password` CHAR(32) DEFAULT NULL COMMENT '密码'
  }
