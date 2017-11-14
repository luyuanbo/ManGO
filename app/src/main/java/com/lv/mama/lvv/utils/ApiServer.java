package com.lv.mama.lvv.utils;


import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.sort.bean.DataleftBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {

    @GET("umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Observable<HomeBean> getHome();

   /* @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<HomeBeans> getHomes();*/

    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap Map<String,String> map);

    @GET
    Observable<DataleftBean> getSortLeftBean(@Url String url);

}