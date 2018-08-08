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
public class Items {

    private String pid;
    private String msg;
    public void setPid(String pid) {
         this.pid = pid;
     }
     public String getPid() {
         return pid;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public Items() {
    }

    public Items(String pid, String msg) {

        this.pid = pid;
        this.msg = msg;
    }
}