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
public class PostAddrItem {

    private String province;
    private String city;
    private String area;
    private String postName;
    private String postPhone;
    private String addr;
    public void setProvince(String province) {
         this.province = province;
     }
     public String getProvince() {
         return province;
     }

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setArea(String area) {
         this.area = area;
     }
     public String getArea() {
         return area;
     }

    public void setPostName(String postName) {
         this.postName = postName;
     }
     public String getPostName() {
         return postName;
     }

    public void setPostPhone(String postPhone) {
         this.postPhone = postPhone;
     }
     public String getPostPhone() {
         return postPhone;
     }

    public void setAddr(String addr) {
         this.addr = addr;
     }
     public String getAddr() {
         return addr;
     }

    public PostAddrItem() {
    }

    public PostAddrItem(String province, String city, String area, String postName, String postPhone, String addr) {

        this.province = province;
        this.city = city;
        this.area = area;
        this.postName = postName;
        this.postPhone = postPhone;
        this.addr = addr;
    }
}