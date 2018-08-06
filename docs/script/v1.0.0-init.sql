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


#财务钱包表
CREATE TABLE `tb_wallets` (
  `wallet_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `balance` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `edit_date` datetime DEFAULT NULL COMMENT '最后更新时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务钱包表';



#交易流水表
CREATE TABLE `tb_transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` varchar(36) NOT NULL COMMENT '流水号(uuid)',
  `record_no` varchar(128) NULL COMMENT '交易号(用来存支付宝、微信以及银行的，站内交易留空)',
  `from_uid` int(11) NOT NULL COMMENT '发起方账户',
  `to_uid` int(11) NOT NULL COMMENT '对方账户',
  `trade_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易日',
  `trade_type` int(11) NOT NULL COMMENT '交易类别(1=充值,2=消费)',
  `trade_amount` decimal(16,4) NOT NULL COMMENT '交易额',
  `remark` varchar(100) NOT NULL COMMENT '摘要',
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易流水表';

#资金变化表
CREATE TABLE `tb_moneys` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT, 
  `record_id` varchar(36) NOT NULL COMMENT '流水号',
  `from_uid` int(11) NOT NULL COMMENT '用户id',
  `trade_type` int(11) NOT NULL COMMENT '1=增 2=减',
  `trade_amount` decimal(16,4) NOT NULL COMMENT '交易额',
  `account_balance` decimal(16,4) NOT NULL COMMENT '余额',
  `remark` varchar(32) NOT NULL COMMENT '摘要',
  `add_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易日',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金变化表';


#重要异常日志表
CREATE TABLE `tb_max_exceptions` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `body` text NOT NULL COMMENT '日志主体',
  `add_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上报时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='重要异常日志表';


#字典表
CREATE TABLE `tb_setting` (
  `id` int(11) NOT NULL,
  `open_tixian` int(11) DEFAULT NULL COMMENT '是否开启提现 1是 0否',
  `tixian_count` int(11) DEFAULT NULL COMMENT '24小时可以提现次数',
  `min_money` double DEFAULT NULL COMMENT '单笔提现最小金额',
  `max_money` double DEFAULT NULL COMMENT '单笔提现最大金额',
  `shouxu` double DEFAULT NULL COMMENT '手续费',
  `min_shouxu` double DEFAULT NULL COMMENT '最小手续费',
  `max_shouxu` double DEFAULT NULL COMMENT '最大手续费',
  `web_name` varchar(30) DEFAULT NULL COMMENT '网站名称',
  `web_url` varchar(50) DEFAULT NULL COMMENT '网站链接',
  `web_logo` varchar(100) DEFAULT NULL COMMENT '网站logo地址',
  `mobile_logo` varchar(100) DEFAULT NULL COMMENT '移动端logo地址',
  `app_logo` varchar(100) DEFAULT NULL COMMENT 'app_logo地址',
  `goods_default_img` varchar(200) DEFAULT NULL COMMENT '商品列表图的默认图片',
  `slide_1_img` varchar(150) DEFAULT NULL COMMENT '幻灯片1地址',
  `slide_1_url` varchar(150) DEFAULT NULL COMMENT '点击幻灯片跳转地址',
  `slide_2_img` varchar(150) DEFAULT NULL COMMENT '幻灯片2',
  `slide_2_url` varchar(150) DEFAULT NULL,
  `slide_3_img` varchar(150) DEFAULT NULL,
  `slide_3_url` varchar(150) DEFAULT NULL,
  `copy_info` varchar(200) DEFAULT '(c)2013-2015 销量联盟 All Rights Reserved' COMMENT '版权信息',
  `vip_member_name` varchar(100) DEFAULT 'VIP' COMMENT 'VIP会员名称',
  `money_name` varchar(20) DEFAULT '人民币' COMMENT '货币名称',
  `money_unit` varchar(5) DEFAULT '元' COMMENT '货币单位',
  `virtual_name` varchar(10) DEFAULT '任务金币' COMMENT '虚拟货币名称',
  `virtual_unit` varchar(5) DEFAULT '个' COMMENT '虚拟货币单位',
  `yongjin_name` varchar(10) DEFAULT NULL COMMENT '佣金名称',
  `message_open` int(11) DEFAULT '1' COMMENT '短信是否开启 1开 0关',
  `alipay_qrcode` varchar(100) DEFAULT NULL COMMENT '支付宝二维码',
  `zhijie_fabu_shiyong` double DEFAULT '0' COMMENT '直接发布试用奖励',
  `jianjie_fabu_shiyong` double DEFAULT '0' COMMENT '间接推荐人发布试用奖励',
  `zhijie_shenqing_shiyong` double DEFAULT '0' COMMENT '直接推荐人申请试用奖励',
  `jianjie_shenqing_shiyong` double DEFAULT '0' COMMENT '间接推荐人申请试用奖励',
  `zhijie_fabu_shiyong_fw` double DEFAULT '0' COMMENT '直接推荐人发布试用奖励',
  `jianjie_fabu_shiyong_fw` double DEFAULT '0' COMMENT '间接推荐人发布试用奖励',
  `shangjia_wancheng_yiji` double DEFAULT '0' COMMENT '商家发布的任务被完成,完成后奖励推广上级人',
  `shike_wancheng_yiji` double DEFAULT '0' COMMENT '试客完成一单给上级返利',
  `shike_wancheng_erji` double DEFAULT '0' COMMENT '试客完成一单给上级返利',
  `yiji_jiangli` double DEFAULT NULL COMMENT '推广一个一级奖励',
  `erji_jiangli` double DEFAULT NULL COMMENT '二级奖励',
  `task_cancel_time` int(11) DEFAULT NULL COMMENT '任务自动取消时间',
  `system_account` int(11) DEFAULT NULL COMMENT '系统账户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#预置数据
INSERT INTO `lianmeng`.`tb_setting` (`id`, `open_tixian`, `tixian_count`, `min_money`, `max_money`, `shouxu`, `min_shouxu`, `max_shouxu`, `web_name`, `web_url`, `web_logo`, `mobile_logo`, `app_logo`, `goods_default_img`, `slide_1_img`, `slide_1_url`, `slide_2_img`, `slide_2_url`, `slide_3_img`, `slide_3_url`, `copy_info`, `vip_member_name`, `money_name`, `money_unit`, `virtual_name`, `virtual_unit`, `yongjin_name`, `message_open`, `alipay_qrcode`, `zhijie_fabu_shiyong`, `jianjie_fabu_shiyong`, `zhijie_shenqing_shiyong`, `jianjie_shenqing_shiyong`, `zhijie_fabu_shiyong_fw`, `jianjie_fabu_shiyong_fw`, `shangjia_wancheng_yiji`, `shike_wancheng_yiji`, `shike_wancheng_erji`, `yiji_jiangli`, `erji_jiangli`, `task_cancel_time`, `system_account`) VALUES ('1', '1', '1', '1', '100', '0.2', '1', '10', '58同城', 'www.baidu.com', 'https://www.baidu.com/img/bd_logo1.png', 'http://192.168.43.181:8081/images/upload/20180722164603774317.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', 'https://www.baidu.com/img/bd_logo1.png', '', '(c)2015-2018 销量联盟 All Rights Reserved', 'VIP', '的', '发', '任务金币', '是', '啊', '1', NULL, '0.3', '0.2', '0.2', '0.2', '0.2', '0.2', '1', '4', '5', '2', '3', NULL, '1');



