package com.lv.mama.lvv.sort.model;

import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 卢总 on 2017/11/13.
 */

public class SortModle implements ISortMode {
    @Override
    public void getUrl(String url) {
        Observable<DataleftBean> sortLeftBean = RetroFactory.getInstance().getSortLeftBean(url);
        sortLeftBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataleftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataleftBean dataleftBean) {

                    }
                });
    }
}
