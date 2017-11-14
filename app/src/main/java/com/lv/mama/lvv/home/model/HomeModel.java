package com.lv.mama.lvv.home.model;

import android.util.Log;

import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 卢总 on 2017/11/10.
 */

public class HomeModel implements IHomeModel {

    private HomeBean.DataBean data;
    public Onfinish onfinish;

    public interface Onfinish{
        void OnFinishListener(HomeBean.DataBean data);
    }

    public void setOnfinish(Onfinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getUrl(String url) {

        Observable<HomeBean> home = RetroFactory.getInstance().getHome();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {

                        Log.d("onCompleted+++++++", "666666666: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError+++++++", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        data = homeBean.getData();
                        onfinish.OnFinishListener(data);
                        Log.d("main+++++++", "onNext: "+homeBean.getData().getAd1().toString());
                    }
                });

    }

}
