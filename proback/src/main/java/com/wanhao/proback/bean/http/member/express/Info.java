/**
  * Copyright 2018 bejson.com 
  */
package com.wanhao.proback.bean.http.member.express;

/**
 * Auto-generated: 2018-08-08 23:6:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Info {

    private String sign;
    private String sid;
    private String username;
    public void setSign(String sign) {
         this.sign = sign;
     }
     public String getSign() {
         return sign;
     }

    public void setSid(String sid) {
         this.sid = sid;
     }
     public String getSid() {
         return sid;
     }

    public void setUsername(String username) {
         this.username = username;
     }
     public String getUsername() {
         return username;
     }

    public Info() {
    }

    public Info(String sign, String sid, String username) {

        this.sign = sign;
        this.sid = sid;
        this.username = username;
    }
}