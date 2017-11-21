package com.lv.mama.lvv.mine.model;

import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.utils.RetroFactoryPost;

import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 卢总 on 2017/11/13.
 */

public class LoginModle implements ILoginModle {

    public Onfinish onfinish;

    public interface Onfinish{
        void Loginfinish(LoginBean loginBean);
    }

    public void setOnfinish(Onfinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getLoginUrl(String url, String uname, String password) {

        HashMap<String,String> map = new HashMap<>();
        map.put("mobile",uname);
        map.put("password",password);

        RetroFactoryPost.getInstance()
                .getLogin(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {


                        onfinish.Loginfinish(loginBean);
                    }
                });

    }
}
