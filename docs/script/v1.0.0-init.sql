#快递平台渠道表
CREATE TABLE `tb_express_platforms` (
  `expp_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '平台名称',
  `url` varchar(255) NOT NULL COMMENT '平台URL',
  `isEnable` int(11) DEFAULT '1' COMMENT '状态',
  `isDelete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`expp_id`),
  UNIQUE KEY `uq_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递平台渠道表';


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
  `isEnable` int(11) DEFAULT '1' COMMENT '状态',
  `isDelete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`business_id`),
  UNIQUE KEY `uq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌电商公司表';



INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`, `isEnable`, `isDelete`) VALUES ('1', '淘宝网', '1', '0');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`, `isEnable`, `isDelete`) VALUES ('2', '天猫商城', '1', '0');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`, `isEnable`, `isDelete`) VALUES ('3', '京东商城', '1', '0');
INSERT INTO `lianmeng`.`tb_business_brands` (`business_id`, `name`, `isEnable`, `isDelete`) VALUES ('4', '拼多多', '1', '0');



#快递平台订单表
CREATE TABLE `tb_express_orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `send_address_id` int(11) NOT NULL COMMENT '发货地址',
  `recv_address` varchar(255) NOT NULL COMMENT '收货地址',
  `express_id` varchar(62) DEFAULT NULL COMMENT '快递单号',
  `amount` decimal(12,2) NOT NULL COMMENT '订单总金额',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态(0=待审核,1=已发货,2=拒绝,3=取消)',
  `add_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_date` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `isEnable` int(11) DEFAULT '1' COMMENT '状态',
  `isDelete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递平台订单表';





#快递发货地址表
CREATE TABLE `tb_express_postal_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL COMMENT '城市id',
  `postal_code` varchar(6) NOT NULL DEFAULT '000000' COMMENT '邮政编码',
  `address` varchar(255) NOT NULL COMMENT '收货地址',
  `real_name` varchar(32) NOT NULL COMMENT '真实姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `sort` int(11) DEFAULT '0' COMMENT '显示顺序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递发货地址表';


