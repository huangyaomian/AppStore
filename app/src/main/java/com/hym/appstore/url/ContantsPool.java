package com.hym.appstore.url;

/**
 * Created by Android on 2016/6/22.
 * 常量池
 */
public interface ContantsPool {


   String baseUrl = "http://testing-api.qoo-app.com";

   /**猜你喜欢**/
   String recommendURL = baseUrl + "/v10/apps?sort=hot&page=1&last=&perpage=30";



}
