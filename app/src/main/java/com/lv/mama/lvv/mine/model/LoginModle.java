package com.lv.mama.lvv.mine.model;

import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.system.MyApplication;
import com.lv.mama.lvv.utils.RetroFactoryPost;
import com.lv.mama.lvv.utils.SPUtils;

import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 卢总 on 2017/11/13.
 */

public class LoginModle implements ILoginModle {

    private String code;
    public Onfinish onfinish;
    private String msg;

    public interface Onfinish{
        void Loginfinish(String code,String msg);
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

                        code = loginBean.getCode();
                        int uid = loginBean.getData().getUid();
                        if (code=="0"){
                            SPUtils.put(MyApplication.getmContext(),"uid",uid+"");
                        }
                        msg = loginBean.getMsg();
                        onfinish.Loginfinish(code,msg);
                    }
                });

    }
}
