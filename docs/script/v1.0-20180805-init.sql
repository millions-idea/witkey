#快递平台表 韦德 2018年8月1日16:11:03
CREATE TABLE `tb_express_platforms` (
  `exp_id` int(11) NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(32) NOT NULL COMMENT '平台名称',
  `platform_url` varchar(255) NOT NULL COMMENT '平台URL',
  PRIMARY KEY (`exp_id`),
  UNIQUE KEY `uq_name` (`platform_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递平台表';

