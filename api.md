# 公用部分

http://101.201.238.147:8080/



### 001 获取验证码

```
kaptcha/captcha-image 
```

请求方式 

###get



###002 判断验证码是否正确

```
kaptcha/checkVerifyCode
```

请求方式  post

```
参数 String verifyCode
```

返回示例：

```成功:
 成功 {
  "flage":true
    }
失败
    {
     "flage":false
    }

```



### 003获取所有省份

```/area
/area/getAllProvince
```

请求方式   get

返回实例

```
[
{"codeid":0,"parentid":0,"cityName":"所在地区"},{"codeid":11,"parentid":0,"cityName":"北京"},{"codeid":12,"parentid":0,"cityName":"天津"},{"codeid":13,"parentid":0,"cityName":"河北"},{"codeid":14,"parentid":0,"cityName":"山西"},{"codeid":15,"parentid":0,"cityName":"内蒙古"},{"codeid":21,"parentid":0,"cityName":"辽宁"},{"codeid":22,"parentid":0,"cityName":"吉林"},{"codeid":23,"parentid":0,"cityName":"黑龙江"},{"codeid":31,"parentid":0,"cityName":"上海"},{"codeid":32,"parentid":0,"cityName":"江苏"},{"codeid":33,"parentid":0,"cityName":"浙江"},{"codeid":34,"parentid":0,"cityName":"安徽"},{"codeid":35,"parentid":0,"cityName":"福建"},{"codeid":36,"parentid":0,"cityName":"江西"},{"codeid":37,"parentid":0,"cityName":"山东"},{"codeid":41,"parentid":0,"cityName":"河南"},{"codeid":42,"parentid":0,"cityName":"湖北"},{"codeid":43,"parentid":0,"cityName":"湖南"},{"codeid":44,"parentid":0,"cityName":"广东"},{"codeid":45,"parentid":0,"cityName":"广西"},{"codeid":46,"parentid":0,"cityName":"海南"},{"codeid":50,"parentid":0,"cityName":"重庆"},{"codeid":51,"parentid":0,"cityName":"四川"},{"codeid":52,"parentid":0,"cityName":"贵州"},{"codeid":53,"parentid":0,"cityName":"云南"},{"codeid":54,"parentid":0,"cityName":"西藏"},{"codeid":61,"parentid":0,"cityName":"陕西"},{"codeid":62,"parentid":0,"cityName":"甘肃"},{"codeid":63,"parentid":0,"cityName":"青海"},{"codeid":64,"parentid":0,"cityName":"宁夏回族自治区"},{"codeid":65,"parentid":0,"cityName":"新疆维吾尔自治区"},{"codeid":71,"parentid":0,"cityName":"台湾"},{"codeid":81,"parentid":0,"cityName":"香港"},{"codeid":91,"parentid":0,"cityName":"澳门"}
]
```



###004 获取省份下的城市

```
/area/getCity?cid=37
```

请求方式  get

#### 返回实例

```
[
{"codeid":3701,"parentid":37,"cityName":"济南"},{"codeid":3702,"parentid":37,"cityName":"青岛"},{"codeid":3703,"parentid":37,"cityName":"淄博"},{"codeid":3704,"parentid":37,"cityName":"枣庄"},{"codeid":3705,"parentid":37,"cityName":"东营"},{"codeid":3706,"parentid":37,"cityName":"烟台"},{"codeid":3707,"parentid":37,"cityName":"潍坊"},{"codeid":3708,"parentid":37,"cityName":"济宁"},{"codeid":3709,"parentid":37,"cityName":"泰安"},{"codeid":3710,"parentid":37,"cityName":"威海"},{"codeid":3711,"parentid":37,"cityName":"日照"},{"codeid":3712,"parentid":37,"cityName":"莱芜"},{"codeid":3713,"parentid":37,"cityName":"临沂"},{"codeid":3714,"parentid":37,"cityName":"德州"},{"codeid":3715,"parentid":37,"cityName":"聊城"},{"codeid":3716,"parentid":37,"cityName":"滨州"},{"codeid":3717,"parentid":37,"cityName":"菏泽"}
]
```



##005 文件上传

```
/fileup/fileUpload
```

请求方式  post

参数类型  MultipartFile file

返回实例

```
正常  {
    url:'localhost:8080/file/upload/20180718221943345645.jpg',   //在前台直接用图片 src 显示
    error:'0',
    path:'20180718221943345645.jpg'    //传到后台保存到数据库
}
失败  {
    error:'1'
}
```



####006获取配置

```
intf_setting/getSetting
```

### 返回示例：

```
成功:
{
    "setting":"{
    \"open_tixian\":1,
    \"tixian_count\":1,
    \"min_money\":1.0,
    \"max_money\":100.0,
    \"shouxu\":0.1,
    \"min_shouxu\":0.1,
    \"max_shouxu\":10.0,\
    "web_name\":\"这是网址\",
    \"web_url\":\"www.baidu.com\",
    \"web_logo\":\"http://192.168.43.181:8081/images/upload/20180722163527664306.png\",
    \"mobile_logo\":\"http://192.168.43.181:8081/images/upload/20180722164603774317.png\",
    \"app_logo\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_1_img\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_2_img\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_3_img\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_1_url\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_2_url\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"slide_3_url\":\"\",\"copy_info\":\"(c)2015-2018 销量联盟 All Rights Reserved\",
    \"vip_member_name\":\"VIP\",
    \"money_name\":\"的\",
    \"money_unit\":\"发\",
    \"virtual_name\":\"个\",
    \"virtual_unit\":\"是\",
    \"yongjin_name\":\"啊\",\"message_open\":1,
    \"goods_default_img\":\"https://www.baidu.com/img/bd_logo1.png\",
    \"do_task_time\":60,
    \"zhijie_fabu_shiyong\":0.3,
    \"jianjie_fabu_shiyong\":0.2,
    \"zhijie_shenqing_shiyong\":0.2,
    \"jianjie_shenqing_shiyong\":0.2,
    \"zhijie_fabu_shiyong_fw\":0.2,
    \"jianjie_fabu_shiyong_fw\":0.2,
    \"shangjia_wancheng_yiji\":1.0,
    \"shike_wancheng_yiji\":4.0,
    \"shike_wancheng_erji\":5.0,
    \"yiji_jiangli\":2.0,
    \"erji_jiangli\":3.0,\"id\":1,
    \"page\":1,\"rows\":10}",
    "error":0,
    "message":"操作完成"
}
```





###007常量定义和使用

所属分类类型

```
淘宝试用 4
阿里巴巴试用 5
京东试用 40
拼多多试用 6
蘑菇街试用 7
美丽说试用 8
淘宝访问 41
阿里巴巴访问 51
京东访问 401
```



## 1、注册

### 请求URL：
	/intf_member/register

### 请求方式：
	POST

### 参数类型

	|参数		|是否必选 |类型     |说明
	is_seller    是      int      是否是商家  	1是 0否
	username     是      string   用户名
	password     是      string   密码  	传rsa加密之后的值
	real_name    是      string   真实姓名  
	email		是 		string  邮箱
	mobile		是		string	手机号
	qq			是		string  qq
	invite_name	 否		string		邀请人用户名
	sheng		是		string  省份
	shi			是		string  市

### 返回示例：
	成功:
	    {
	      "error": 0,
	      "message","注册成功"
	    }
	 失败
	 {
	      "error": 1,
	      "message","该手机号已经注册!"
	  }

-----------------------------------------------------------------





## 2、登陆

### 请求URL：
	intf_member/login

### 请求方式：
	POST

### 参数类型

	|参数		|是否必选 |类型     |说明
	|username    |Y       |string   |用户名
	|password    |Y       |string   |密码

### 返回示例：
	成功:
	  {
	    "token": "f89b6814-2298-420a-a27d-81625ac3c0c2",
	    "error": "0",
	    "message": "登录成功!",
	    "member": "{\"username\":\"张三\",\"login_ip\":\"0:0:0:0:0:0:0:1null\",\"email\":\"34433\",\"mobile\":\"234234\",\"money\":0.0,\"mobile_code\":\"-1\",\"pass_code\":\"-1\",\"login_time\":\"Jul 16, 2018 1:04:45 PM\",\"status\":0,\"add_moneys\":0.0,\"gender\":\"男士\",\"loin_count\":1,\"pass_exam\":0,\"is_real_name\":0,\"is_real_mobile\":0,\"is_real_bank\":0,\"vipmodel\":\"游客\",\"version\":1,\"token\":\"f89b6814-2298-420a-a27d-81625ac3c0c2\",\"id\":1,\"page\":1,\"rows\":10}"
	}
	失败
	    {
	      "error": 1,
	      "message": "用户名或密码错误"
	    }



#### 2.1获取用户信息

### 请求URL：

```
intf_member/getMemberInfo
```

### 请求方式：

```
POST/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int	 	用户Id

```

### 返回示例：

```
成功:
  {
    "error": "0",
    "message": "查询成功!",
    "member": "{\"username\":\"张三\",\"login_ip\":\"0:0:0:0:0:0:0:1null\",\"email\":\"34433\",\"mobile\":\"234234\",\"money\":0.0,\"mobile_code\":\"-1\",\"pass_code\":\"-1\",\"login_time\":\"Jul 16, 2018 1:04:45 PM\",\"status\":0,\"add_moneys\":0.0,\"gender\":\"男士\",\"loin_count\":1,\"pass_exam\":0,\"is_real_name\":0,\"is_real_mobile\":0,\"is_real_bank\":0,\"vipmodel\":\"游客\",\"version\":1,\"token\":\"f89b6814-2298-420a-a27d-81625ac3c0c2\",\"id\":1,\"page\":1,\"rows\":10}"
}

```



## 3、实名

### 请求URL：
	intf_member/realName

### 请求方式：
	POST

### 参数类型：

	|参数		|是否必选 |类型     	|说明
	mobile		是      	string   用户的手机号(放到请求头里面)
	token		是      	string   登录返回的(放到请求头里面)
	realname	是		string  真实姓名
	idcard		是		string	身份证号
	url1		是		string	身份证正面的url图片地址
	url2		是		string	身份证反面
	url3		是		string	手持身份证


### 返回示例：
	成功:
	   {
	      "error": 0,
	      "message","操作完成"
	    }
	失败
	    {
	      "error": 1,
	      "message": "信息不完整"
	    }





## 4、绑定买家号

### 请求URL：
	intf_member/bindBuyAccount

### 请求方式：
	post

### 参数类型

	|参数		|是否必选 |类型     	|说明
	mobile		是      	string   用户的手机号(放到请求头里面)
	token		是      	string   登录返回的(放到请求头里面)
	catid		是		int  	类型id
	account		是		string	买号名称
	url1		否		string	截图1
	url2		否		string	截图1
	url3		否		string	截图1
	url4		否		string	截图1
	url5		否		string	截图1
	account_gender是		string	买号性别
	honor		是		string	信誉值
	age_range	是		string	年龄范围  12345
	taoqi		否		int		淘气值
	truename	是		string	买号姓名
	mobile		是		string	手机号
	province	是		string	省名称
	city		是		string	市名称
	address		是		string	详细收货地址


### 返回示例：   信誉 常买为空
```json
成功:
   {
      "error": 0,
      "message","操作完成,下一步由管理员审核"
    }
失败
    {
      "error": 1,
      "message": "该账号已经绑定过了! 不要重复绑定"
    }
```



## 5、获取用户买号列表

### 请求URL：
	intf_member/queryBuyAccountList

### 请求方式：
	post

### 参数类型: query

	|参数		|是否必选 |类型     |说明
	mobile		是      	string   用户的手机号(放到请求头里面)
	token		是      	string   登录返回的(放到请求头里面)
	memid		是		int		用户id


### 返回示例：
	{
	   
	}

###5.1删除用户买号

### 请求URL：

```
intf_account/deleteBuyAccount

```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
account_id	是		int		买号id
```

### 返回示例：

```
成功
{
    error:0,
    message:'删除买号成功'
}
失败
{
    error:1,
    message:'请选择要删除的买号'
}
```

### 5.2 禁用/启用 用户买号

### 请求URL：

```
intf_account/disableAccount
```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
account_id	是		int		买号id
```

### 返回示例：

```
成功
{
    error:0,
    message:'修改完成'
}
失败
{
    error:1,
    message:'参数不完整'
}

```







## 6、绑定银行卡

### 请求URL：
	intf_member/bindBank

### 请求方式：
	post

### 参数类型
	|参数		|是否必选 |类型     |说明
	mobile		是      	string   用户的手机号(放到请求头里面)
	token		是      	string   登录返回的(放到请求头里面)
	memid		是		int		用户id
	bank_type	是		string	银行卡类型 如 农业银行 工商
	bank_num	是		string	银行卡号
	bank_mobile	是		string	银行预留手机号
	bank_username是		string	开户人姓名
	bank_id_card 是	string 		身份证号


### 返回示例：
	正常{
	    "error": 0,
	    "message","已经提交,等待审核"
	}
	错误
	{
	    "error": 1,
	    "message","已经绑定过,不能重复绑定"
	}

## 7、 绑定店铺



### 请求URL：
	intf_member/bindShop

### 请求方式：
	post

### 参数类型
	|参数		|是否必选 |类型     |说明
	mobile		是      	string   用户的手机号(放到请求头里面)
	token		是      	string   登录返回的(放到请求头里面)
	memid		是		int		用户id
	shop_type	是		string	店铺类型 淘宝 京东
	shop_name	是		string	店铺名称
	shop_url	是		string	店铺网址
	shop_wangwang是		string	店铺联系方式
	send_province是		string	发货省
	send_city	是		string 发货的市
	send_town	是		string	发货的乡镇
	shop_img	是		string	店铺图片

### 返回示例：
	正常{
	    "error": 0,
	    "message","已经提交,等待审核"
	}
	错误
	{
	    "error": 1,
	    "message","已经绑定过,不能重复绑定"
	}

## #7.1、 删除店铺

### 请求URL：

```
intf_shop/deleteShop

```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户id
shop_id		是		int		店铺id
```

### 返回示例：

```
正常{
    "error": 0,
    "message","操作完成"
}
错误
{
    "error": 1,
    "message","参数不完整"
}

```

## #7.2、 查看店铺列表

### 请求URL：

```
intf_member/getShopList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户id
```

### 返回示例：

```
正常{
    "error": 0,
    "message","查询完成",
    list:[
        { 
        和数据库的表字段一样
        },
    ]
}
错误
{
    "error": 1,
    "message","参数不完整"
}


```





##8绑定收款账号

### 请求URL：

```
intf_member/bindCash
```

### 请求方式：

```
post
```

### 参数类型



```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户id
alipay		是		string	支付宝账号
bank_type	是		string	银行类型
bank_num	是		string	银行卡号
bank_province是		string	银行卡省
bank_city	是		string	银行卡市
bank_addr	是		string	地址

```

### 返回示例：

```
正常{
    "error": 0,
    "message","已经提交,等待审核"
}
错误
{
    "error": 1,
    "message","已经绑定过,不能重复绑定"
}
```



##9获取vip列表

### 请求URL：

```
intf_vip/loadList
```

### 请求方式：

```
get
```

### 参数类型

```
无
```

### 返回示例：

```
[
{"vipmodel":"试客会员","sale_mode":"免费","vip_type":"试客会员","kuaidi_limit":"不限","bai_tian_price":0,"vip_flag":0,"dizhi_limit":1,"day_limit":2,"week_limit":5,"month_limit":10,"sum_price_limit":300,"id":1,"page":1,"rows":10},

{"vipmodel":"荣誉试客会员","sale_mode":"收费","vip_type":"试客会员","kuaidi_limit":"不限","bai_tian_price":10,"vip_flag":1,"dizhi_limit":1,"day_limit":4,"week_limit":6,"month_limit":20,"sum_price_limit":500,"id":2,"page":1,"rows":10},

{"vipmodel":"银牌试客会员","sale_mode":"收费","vip_type":"试客会员","kuaidi_limit":"不限","bai_tian_price":20,"vip_flag":1,"dizhi_limit":1,"day_limit":5,"week_limit":10,"month_limit":30,"sum_price_limit":800,"id":3,"page":1,"rows":10},

{"vipmodel":"金牌试客会员","sale_mode":"收费","vip_type":"试客会员","kuaidi_limit":"不限","bai_tian_price":30,"vip_flag":1,"dizhi_limit":1,"day_limit":5,"week_limit":15,"month_limit":30,"sum_price_limit":999999999,"id":4,"page":1,"rows":10}
]

```



##10 密码管理



### 请求URL：

```
intf_member/passwordManager

```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
new_pass	否		string		新密码
old_pass	否		string		旧密码
new_pay_pass 否		string		新支付密码
old_pay_pass 否		string		旧支付密码

```

### 返回示例：

```

正常{
    "error": 0,
    "message","密码修改完成"
}
错误 
{
    "error": 0,
    "message","操作完成"
}
```



## 11 获取一级推广下线列表



### 请求URL：

```
intf_member/loadFirstInviteList

```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
```

### 返回示例：

```
正常{
    "error": 0,
    "message","操作完成",
    "list":[
        {id:1,username:'yi'....................},
        {}
    ]
}
错误 
{
    "error": 1,
    "message","参数不完整"
}
```



## 12 获取二级推广下线列表



### 请求URL：

```
intf_member/loadSecondInviteList

```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
```

### 返回示例：

```
正常{
    "error": 0,
    "message","操作完成",
    "list":[
        {id:1,username:'yi'....................},
        {}
    ]
}
错误 
{
    "error": 1,
    "message","参数不完整"
}
```



####12.1获取推广链接

### 请求URL：

```
intf_member/loadSecondInviteList
```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id

```

### 返回示例：

```
正常{
    "error": 0,
    "message","操作完成",
    "url":
}

```





## 13 周推广用户列表数据



### 请求URL：

```
intf_member/loadInviteByWeek
```

### 请求方式：

```
get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)

```

### 返回示例：

```
{
    "list":
    "[{\"pep_count\":4,\"invite_id\":6,\"username\":\"张三\"},{\"pep_count\":1,\"invite_id\":5,\"username\":\"test6\"},{\"pep_count\":1,\"invite_id\":1,\"username\":\"a\"},{\"pep_count\":1,\"invite_id\":14,\"username\":\"买家222\"},{\"pep_count\":1,\"invite_id\":3,\"username\":\"test66\"}]",
    "error":0,
    "message":"查询成功"
}
```



## 14 月推广用户列表数据



### 请求URL：

```
intf_member/loadInviteByMonth
```

### 请求方式：

```
get/post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
无
```

### 返回示例：

```
{
    "list":
    "[{\"pep_count\":4,\"invite_id\":6,\"username\":\"张三\"},{\"pep_count\":1,\"invite_id\":5,\"username\":\"test6\"},{\"pep_count\":1,\"invite_id\":1,\"username\":\"a\"},{\"pep_count\":1,\"invite_id\":14,\"username\":\"买家222\"},{\"pep_count\":1,\"invite_id\":3,\"username\":\"test66\"}]",
    "error":0,
    "message":"查询成功"
}

```



## 15获取任务列表



### 请求URL：

```
intf_shop/loadUserTaskList
```

### 请求方式：

```
get/post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
memid		是		int		用户Id
class_type	否		int		商品类目
order_by	否		string	排序方式 1最新 2价值 3奖励
```

### 返回示例：

```java
Goods.java	
	private String catetype;
    private String link_url;
    private String goods_img;
    private String search_word;
    private String goods_format;
    private String template_name;
    private Integer shop_id;
    private Integer goods_class_id;
    private Integer search_type;
    private Integer task_count;
    private Integer return_type;
    private Integer pay_type;
    private Integer time_divider;
    private Integer task_safe;
    private Integer save_template;
    private Integer memid;
    private Double price;
    private Double append_money;

    @Transient
    private TryRequire tryRequire;
    @Transient
    private BuyerRequire buyerRequire;

```

```java
TryRequire.java

    private Integer zhiding_pinglun;
    private Integer bask_img;
    private Integer task_rec_time;
    private Integer memid;
    private Integer goods_id;
    private String device;
    private Integer need_bi_san_jia;
    private Integer need_chat;
    private Integer need_look_comment;
    private Integer need_add_buy_cart;
    private Integer need_col_goods;
    private String yuyue_xiadan;
    private String comment_content;
    private String confirm_time_type;
    private String remark;

```



```java
BuyerRequire.java
 	private Integer day_limit;
    private Integer shangbao_limit;
    private Integer taoqi_limit;
    private Integer gender_limit;
    private Integer memid;
    private Integer goods_id;
    private String forbidden_area;
    private String honor_limit;
    private String age_limit;
    private String always_buy_class;

```

```
list 里面的是 数组 
	list: [
		{
		catetype:'xxx',
            link_url:'baidu.com',
            tryRequire:{
                device:'手机',
                goods_id:111,
                zhiding_pinglun:1
            },
            buyerRequire:{
                goods_id:111,
                age_limit:'25-35岁'
            }
		},
		......
	]
	
{
	"list":"[
		{\"catetype\":\"4\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件							\",\"shop_id\":15,\"goods_class_id\":9,\"search_type\":2,\"task_count\":8,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":10,\"price\":457.15171356666673,\"append_money\":1.2911139566997598,
		\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":10,\"goods_id\":1,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":1,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":470,\"memid\":10,\"goods_id\":1,\"forbidden_area\":\"北京\",\"id\":1,\"page\":1,\"rows\":10},\"id\":1,\"page\":1,\"rows\":10},{\"catetype\":\"5\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":20,\"goods_class_id\":2,\"search_type\":0,\"task_count\":6,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":16,\"price\":986.8623400860556,\"append_money\":2.2388445598920024,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":16,\"goods_id\":2,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":2,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":107,\"memid\":16,\"goods_id\":2,\"forbidden_area\":\"北京\",\"id\":2,\"page\":1,\"rows\":10},\"id\":2,\"page\":1,\"rows\":10},{\"catetype\":\"6\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":36,\"goods_class_id\":6,\"search_type\":1,\"task_count\":16,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":28,\"price\":144.52485128433324,\"append_money\":8.475934997796758,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":28,\"goods_id\":3,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":3,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":532,\"memid\":28,\"goods_id\":3,\"forbidden_area\":\"北京\",\"id\":3,\"page\":1,\"rows\":10},\"id\":3,\"page\":1,\"rows\":10},{\"catetype\":\"4\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":38,\"goods_class_id\":4,\"search_type\":1,\"task_count\":7,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":1,\"memid\":1,\"price\":214.0400894874739,\"append_money\":4.176403629876827,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":1,\"goods_id\":4,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":4,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":386,\"memid\":1,\"goods_id\":4,\"forbidden_area\":\"北京\",\"id\":4,\"page\":1,\"rows\":10},\"id\":4,\"page\":1,\"rows\":10},{\"catetype\":\"3\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":4,\"goods_class_id\":8,\"search_type\":2,\"task_count\":8,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":7,\"price\":288.7689089585109,\"append_money\":5.811281634714013,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":7,\"goods_id\":5,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":5,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":372,\"memid\":7,\"goods_id\":5,\"forbidden_area\":\"北京\",\"id\":5,\"page\":1,\"rows\":10},\"id\":5,\"page\":1,\"rows\":10},{\"catetype\":\"1\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":14,\"goods_class_id\":6,\"search_type\":1,\"task_count\":17,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":14,\"price\":480.4223719885996,\"append_money\":6.889596614563155,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":14,\"goods_id\":6,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":6,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":73,\"memid\":14,\"goods_id\":6,\"forbidden_area\":\"北京\",\"id\":6,\"page\":1,\"rows\":10},\"id\":6,\"page\":1,\"rows\":10},{\"catetype\":\"5\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":39,\"goods_class_id\":7,\"search_type\":0,\"task_count\":9,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":1,\"memid\":25,\"price\":135.90080924847126,\"append_money\":9.003613797501123,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":25,\"goods_id\":7,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":7,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":419,\"memid\":25,\"goods_id\":7,\"forbidden_area\":\"北京\",\"id\":7,\"page\":1,\"rows\":10},\"id\":7,\"page\":1,\"rows\":10},{\"catetype\":\"3\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":37,\"goods_class_id\":5,\"search_type\":0,\"task_count\":17,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":13,\"price\":151.19421245045473,\"append_money\":8.501943917637798,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":13,\"goods_id\":8,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":8,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":418,\"memid\":13,\"goods_id\":8,\"forbidden_area\":\"北京\",\"id\":8,\"page\":1,\"rows\":10},\"id\":8,\"page\":1,\"rows\":10},{\"catetype\":\"8\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":20,\"goods_class_id\":5,\"search_type\":1,\"task_count\":0,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":1,\"memid\":15,\"price\":962.7484833285637,\"append_money\":0.676886881565052,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":15,\"goods_id\":9,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":9,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":75,\"memid\":15,\"goods_id\":9,\"forbidden_area\":\"北京\",\"id\":9,\"page\":1,\"rows\":10},\"id\":9,\"page\":1,\"rows\":10},{\"catetype\":\"7\",\"link_url\":\"www.taobao.com\",\"search_word\":\"随便搜\",\"goods_format\":\"件\",\"shop_id\":16,\"goods_class_id\":5,\"search_type\":2,\"task_count\":22,\"return_type\":0,\"pay_type\":1,\"task_safe\":0,\"save_template\":0,\"memid\":27,\"price\":135.58748293352062,\"append_money\":0.33346105985428864,\"tryRequire\":{\"zhiding_pinglun\":1,\"bask_img\":0,\"memid\":27,\"goods_id\":10,\"device\":\"手机\",\"need_bi_san_jia\":0,\"need_chat\":1,\"need_look_comment\":1,\"need_add_buy_cart\":1,\"need_col_goods\":1,\"comment_content\":\"收到东西了,很好用,也很喜欢\",\"confirm_time_type\":\"1天\",\"id\":10,\"page\":1,\"rows\":10},\"buyerRequire\":{\"day_limit\":0,\"shangbao_limit\":0,\"taoqi_limit\":109,\"memid\":27,\"goods_id\":10,\"forbidden_area\":\"北京\",\"id\":10,\"page\":1,\"rows\":10},\"id\":10,\"page\":1,\"rows\":10}]",
	"error":0,
	"message":"操作完成"
}

如果是商家没有发布任务

{
    "error":0,
    "message":"您还没有发布任务"
}

```



## 16 商家发布任务



### 请求URL：

```
intf_shop/pubTask
```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户id
catetype	是 		string	所属分类 是淘宝试用还是京东试用
link_url	是		string	商品链接地址
goods_img	是		string	商品图片
search_word	是		string	搜索关键字
shop_id		是		int		店铺id
shop_name	是		string	店铺名称
goods_format 是		string	商品规格
template_name否		string	模板名称
goods_type	是		int		商品所属宝贝分类
search_type	是		int		搜索类型
task_count	是		int		任务数量
return_type 是		int		返款方式
pay_type	是		int		支付方式
time_divider是		int		发布时间间隔
task_safe	是		int		任务保险 0不需要1全保 2半保
save_template是		int		是否保存模板 1是 0否
price		是		double	商品价格
tao_kouling	 否		string	淘口令
append_money是		double	追加资金
zhiding_pinglun是	int		是否指定评论 1是 0否
bask_img	是		int		是否需要晒图 1是 0否
task_rec_time是		int		任务回收时间
device		是		string  设备要求
need_bi_san_jia是	int		货比三家 1是 0否
need_chat	是		int		拍前聊天 1是 0否
need_look_comment是	int		拍前浏览评论1是 0否
need_add_buy_cart是	int		添加购物车1是 0否
need_col_goods是		int		收藏商品1是 0否
yuyue_xiadan  是		string	预约下单
comment_content否	string	评论内容
confirm_time_type是   string  确认收货时间
remark		  否		string	备注内容
day_limit	 是		int		隔的天数
shangbao_limit是		int		是否需要交纳保证金的买家
taoqi_limit	  否		int		淘气值要求
gender_limit 是		int		性别限制 1男 2女 0不限
honor_limit 是		string	买号信用要求
forbidden_area 否	string[]	禁止某地区的人接
age_limit   否		string	年龄段限制  例如1 2  4 5 6
always_buy_class否	string	买号常买的类型
```

### 返回示例：

```
{
   
}

```

## 17 增加保证金

### 请求URL：

```
intf_member/addPermissMoney
```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
add_val		是		double	要增加的保证金钱数
```

### 返回示例：

```
正常{
    "error": 0,
    "message","保证金缴纳成功",
    "member":{id:100.........}     //最新用户信息
}
错误 
{
    "error": 1,
    "message","金额错误"
}

```

## 18 查看提现记录

### 请求URL：

```
intf_member/tiXianJiLu
```

### 请求方式：

```
post

```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
from_date	 否		string	查询的开始日期
to_date		 否		string	查询的结束日期
page		否		int		查询的页码
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
    "sumMoney":100,
    "sumShouXu":100,
    "pageinfo":{
        
    }
}
错误 
{
    "error": 1,
    "message","没有记录"
}

```

## 19 提现

### 请求URL：

```
intf_member/tixian2Account
```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
money		是		double	提现金额
sk_fangshi	 是		string		提现方式
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"提现已经提交,等待审核(1工作日左右到账)"
}
错误 
{
    "error": 1,
    "message","没有开启提现功能..."
}

```

## 20 试用申请(返回符合条件的买号列表)

### 请求URL：

```
intf_shop/preRecvTask
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
goods_id	是		int		任务id

```

### 返回示例：

```
正常
{
"list":
	"[{\"mem_id\":6,\"age_range\":\"18-25\",\"account_gender\":\"男号\",\"honor\":\"xin9\",\"province\":\"0\",\"town\":\"555\",\"truename\":\"万豪\",\"mobile\":\"15753995256\",\"address\":\"43343\",\"account_type\":\"淘宝试用\",\"account\":\"第三个\",\"is_pass\":1,\"create_time\":\"Jul 19, 2018 4:35:24 PM\",\"remark\":\"                            操作原因\\n                 \",\"version\":1,\"is_delete\":0,\"disable\":1,\"id\":2,\"page\":1,\"rows\":10},{\"mem_id\":6,\"age_range\":\"18-25\",\"shoot_real_name\":\"20180718221940911802.jpg\",\"shoot_zhi_ma\":\"20180718221943345645.jpg\",\"account_gender\":\"男号\",\"honor\":\"xin1\",\"shoot_honor\":\"20180718221945527553.jpg\",\"taoqi\":66,\"province\":\"0\",\"town\":\"555\",\"shoot_taoqi\":\"20180718221949153970.jpg\",\"truename\":\"万豪\",\"mobile\":\"15753995256\",\"address\":\"66666\",\"catid\":\"4\",\"account_type\":\"淘宝试用\",\"account\":\"第一个\",\"is_pass\":1,\"create_time\":\"Jul 17, 2018 4:35:18 PM\",\"remark\":\"                            操作原因\\n                 \",\"version\":1,\"is_delete\":0,\"disable\":1,\"id\":4,\"page\":1,\"rows\":10},{\"mem_id\":6,\"shoot_real_name\":\"\",\"shoot_zhi_ma\":\"20180722151231788963.jpg\",\"account_gender\":\"女号\",\"honor\":\"xin1\",\"shoot_honor\":\"\",\"taoqi\":54,\"city\":\"0\",\"shoot_taoqi\":\"\",\"truename\":\"管竟要\",\"pro_name\":\"0\",\"address\":\"空间和空间\",\"account_type\":\"淘宝试用\",\"account\":\"465165\",\"is_pass\":1,\"remark\":\"                            操作原因\\n     \",\"version\":1,\"shoot_huabei\":\"\",\"is_delete\":0,\"disable\":1,\"id\":5,\"page\":1,\"rows\":10}]",
    "error":0,
    "message":"查询成功"
}
错误 
{
    "error": 1,
    "message","您没有对应的买号,先绑定买号吧"
}


```

## 21 使用买号申请试用

### 请求URL：

```
intf_shop/pickTask
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
goods_id	是		int		任务id
buy_id		是		int		买号的id
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"任务接取成功"
}
错误 
{
    "error": 1,
    "message","你的账户不符合条件"
}


```

###

###22确认收货

### 请求URL：

```
intf_shop/confirmRecvGoods

```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
task_id		是		int		任务id

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"确认成功"
}
错误 
{
    "error": 1,
    "message","确认失败"
}


```

### 23获取已申请列表



### 请求URL：

```
intf_shop/getUserApplyTaskList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
    "pageinfo":{
        list:[ {} ]
    }
}
错误 
{
    "error": 1,
    "message","信息不完整"
}



```

### 

###  24获取试用完成列表

### 请求URL：

```
intf_shop/getFinshedTaskList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页页码

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
    "pageinfo":{
        list:[数据库的字段]
    }
}
错误 
{
    "error": 1,
    "message","参数不完整"
}


```

###   25获取已收货列表

### 请求URL：

```
intf_shop/getFinshedRecvList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页页码

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
    "pageinfo":{
        list:[数据库的字段]
    }
}
错误 
{
    "error": 1,
    "message","参数不完整"
}


```

###   25获取已下单列表

### 请求URL：

```
intf_shop/getFinshedOrderList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页页码


```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
    "pageinfo":{
        list:[数据库的字段]
    }
}
错误 
{
    "error": 1,
    "message","参数不完整"
}



```

###  26任务开始

### 请求URL：

```
intf_shop/getUserTaskStep
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
current_step 是		int		当前任务步数 0为开始
task_id		是		int		任务id
params		否		string[] 前台传过来的参数数组
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     taskStep: "{"allStep":6,"currentStep":0,"step":"remark","stepInfos":["不用联系我,好好做就行"]}"
}
错误 
{
    "error": 1,
    "message","已经是第一步了"
}



```

###   27商家获取待申请列表

### 请求URL：

```
intf_shop/getShopperUnApplyList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     pageinfo: {
         list: 数据库的tb_member_goods
     }
}
错误 
{
    "error": 1,
    "message","没有待申请数据"
}

```

###  28商家获已申请列表

### 请求URL：

```
intf_shop/getShopperAppliedList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     pageinfo: {
         list: 数据库的tb_member_task
     }
}
错误 
{
    "error": 1,
    "message","没有待申请数据"
}

```

###  29商家获已下单列表

### 请求URL：

```
intf_shop/getShopperOrderedList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页


```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     pageinfo: {
         list: 数据库的tb_member_task
     }
}
错误 
{
    "error": 1,
    "message","没有待申请数据"
}

```

###   30商家获已确认收货列表

### 请求URL：

```
intf_shop/getShopperRecvedList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页


```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     pageinfo: {
         list: 数据库的tb_member_task
     }
}
错误 
{
    "error": 1,
    "message",""
}

```

###   31商家获已确认收货列表

### 请求URL：

```
intf_shop/getShopperFinshedList
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
page		否		int		分页


```

### 返回示例：

```
正常
{
    "error":0,
    "message":"查询成功",
     pageinfo: {
         list: 数据库的tb_member_task
     }
}
错误 
{
    "error": 1,
    "message",""
}

```

###   32商家确认用户完成试用

### 请求URL：

```
intf_shop/confirmTask
```

### 请求方式：

```
post/get
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
task_id		是		int		任务Id

```

### 返回示例：

```
正常
{
    "error":0,
    "message":"确认成功"
}
错误 
{
    "error": 1,
    "message","确认失败"
}

```

###  33 刷手 ---> 卖家评论

### 请求URL：

```
intf_shop/appraiseForSeller
```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
memid		是		int		用户的id
task_id		是		int		任务Id
content		是		int		评论内容 1好评 2差评 0中评
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"评价成功"
}
错误 
{
    "error": 1,
    "message","试用未完成不能评价/已经评论过了"
}

```

###  34 卖家 ---> 刷手评论

### 请求URL：

```
intf_shop/appraiseForBuyer
```

### 请求方式：

```
post
```

### 参数类型

```
|参数		|是否必选 |类型     |说明
mobile		是      	string   用户的手机号(放到请求头里面)
token		是      	string   登录返回的(放到请求头里面)
task_id		是		int		任务Id
content		是		int		评论内容 1好评 2差评 0中评
```

### 返回示例：

```
正常
{
    "error":0,
    "message":"评价成功"
}
错误 
{
    "error": 1,
    "message","试用未完成不能评价/已经评论过了"
}

```

### 