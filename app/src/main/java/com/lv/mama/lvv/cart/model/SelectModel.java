package com.lv.mama.lvv.cart.model;


import com.lv.mama.lvv.cart.Bean.Select;
import com.lv.mama.lvv.utils.Api;
import com.lv.mama.lvv.utils.ApiServer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentong on 2017/11/17.
 */

public class SelectModel implements ISelectModel{


    public interface Setdate{
        void getCartdate(Select cartGoods);
    }
    public Setdate setdate;

    public SelectModel(Setdate setdate) {
        this.setdate = setdate;
    }

    @Override
    public void GetCartDate(String uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.TYPE_SORT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiService = retrofit.create(ApiServer.class);
        Observable<Select> getcartgoods = apiService.getcartgoods(uid);
        getcartgoods.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Select>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Select select) {
                        setdate.getCartdate(select);
                    }
                });
    }

}
