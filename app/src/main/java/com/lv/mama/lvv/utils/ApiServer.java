package com.lv.mama.lvv.utils;


import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.cart.Bean.CartBean;
import com.lv.mama.lvv.products.bean.DetailsBean;
import com.lv.mama.lvv.products.bean.ProductsBean;
import com.lv.mama.lvv.sort.bean.KindBean;
import com.lv.mama.lvv.sort.bean.RightBean;

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

    @GET("product/getCatagory")
    Observable<KindBean> getkd();
    @POST
    Observable<RightBean> getkdRight(@Url String str, @QueryMap Map<String,String> map);

    @POST
    Observable<ProductsBean> getProducts(@Url String url, @QueryMap Map<String,String> map);


    @POST
    Observable<DetailsBean> getDetails(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<CartBean> getCart(@Url String url, @QueryMap Map<String,String> map);

}
