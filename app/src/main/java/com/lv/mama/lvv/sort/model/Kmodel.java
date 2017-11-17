package com.lv.mama.lvv.sort.model;


import com.lv.mama.lvv.sort.bean.KindBean;
import com.lv.mama.lvv.sort.bean.RightBean;
import com.lv.mama.lvv.utils.RetroFactorySort;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2017/11/14.
 */

public class Kmodel implements IKmod{
    public interface onFinshkind{
        void Finshkind(List<KindBean.DataBean> data);
    }
    private onRightkind onrightkind;

    public void SetRightkind(onRightkind onrightkind) {
        this.onrightkind = onrightkind;
    }
    public interface onRightkind{
        void Rigthkind(List<RightBean.DataBean> data);
    }
    private onFinshkind onfinshkind;

    public void Setkind(onFinshkind onfinshkind) {
        this.onfinshkind = onfinshkind;
    }

    @Override
    public void getKind(String url) {
        Observable<KindBean> getkd = RetroFactorySort.getInstance().getkd();
        getkd.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KindBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(KindBean kindBean) {
                        List<KindBean.DataBean> data = kindBean.getData();
                        onfinshkind.Finshkind(data);
                    }
                });
    }

    @Override
    public void getKindrig(Map<String,String> msp, String url) {
        Observable<RightBean> kindBeanObservable = RetroFactorySort.getInstance().getkdRight(url,msp);
        kindBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        List<RightBean.DataBean> data = rightBean.getData();

                        onrightkind.Rigthkind(data);
                    }
                });
    }
}
