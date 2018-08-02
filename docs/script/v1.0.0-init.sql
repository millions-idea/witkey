#快递平台渠道表
CREATE TABLE `tb_express_platforms` (
  `expp_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '平台名称',
  `url` varchar(255) NOT NULL COMMENT '平台URL',
  `isEnable` int(11) DEFAULT '1' COMMENT '状态',
  `isDelete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`expp_id`),
  UNIQUE KEY `uq_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='快递平台渠道表';

INSERT INTO `lianmeng`.`tb_express_platforms` (`expp_id`, `name`, `url`, `isEnable`, `isDelete`) VALUES ('1', '空包1000网', 'http://www.kongbao10000.com', '1', '0');
INSERT INTO `lianmeng`.`tb_express_platforms` (`expp_id`, `name`, `url`, `isEnable`, `isDelete`) VALUES ('2', '刷宝网', 'http://www.shuabaokb.com', '0', '0');




#快递平台商品表
CREATE TABLE `tb_express_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `expp_id` int(11) NOT NULL COMMENT '快递空包公司表ID',
  `category_id` int(11) NOT NULL COMMENT '电商公司ID',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` decimal(12,2) NOT NULL COMMENT '单价',
  `rate` int(11) NOT NULL COMMENT '利率',
  `isEnable` int(11) DEFAULT '1' COMMENT '状态',
  `isDelete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递平台商品表';




#电商公司品牌表
CREATE TABLE `tb_business_brands` (
  `business_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '电商公司名称',
  PRIMARY KEY (`business_id`),
  UNIQUE KEY `uq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电商公司品牌表';



INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`) VALUES ('1', '淘宝网');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`) VALUES ('2', '天猫商城');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`) VALUES ('3', '京东商城');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`) VALUES ('4', '拼多多');
