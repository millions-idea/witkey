/**
  * Copyright 2018 bejson.com 
  */
package com.wanhao.proback.bean.http.member.express;
import java.util.List;

/**
 * Auto-generated: 2018-08-08 23:6:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class KongBaoExpressHttpParam {

    private Info info;
    private int kdid;
    private int num;
    private String kg;
    private List<Items> items;
    private PostAddrItem postAddrItem;
    public void setInfo(Info info) {
         this.info = info;
     }
     public Info getInfo() {
         return info;
     }

    public void setKdid(int kdid) {
         this.kdid = kdid;
     }
     public int getKdid() {
         return kdid;
     }

    public void setNum(int num) {
         this.num = num;
     }
     public int getNum() {
         return num;
     }

    public void setKg(String kg) {
         this.kg = kg;
     }
     public String getKg() {
         return kg;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setPostAddrItem(PostAddrItem postAddrItem) {
         this.postAddrItem = postAddrItem;
     }
     public PostAddrItem getPostAddrItem() {
         return postAddrItem;
     }

}