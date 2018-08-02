#快递平台渠道表
CREATE TABLE `tb_express_platforms` (
  `expp_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '平台名称',
  `url` varchar(255) NOT NULL COMMENT '平台URL',
  PRIMARY KEY (`expp_id`),
  UNIQUE KEY `uq_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递平台渠道表';

INSERT INTO `lianmeng`.`tb_express_platforms` (`expp_id`, `name`, `url`) VALUES ('1', '空包1000网', 'http://www.kongbao10000.com');
INSERT INTO `lianmeng`.`tb_express_platforms` (`expp_id`, `name`, `url`) VALUES ('2', '刷宝网', 'http://www.shuabaokb.com');
