package com.lv.mama.lvv.mine.presenter;

import com.lv.mama.lvv.bean.LoginBean;
import com.lv.mama.lvv.mine.model.LoginModle;
import com.lv.mama.lvv.mine.view.ILoginView;

/**
 * Created by 卢总 on 2017/11/13.
 */

public class LoginPresenter implements LoginModle.Onfinish {
    private final ILoginView iLoginView;
    private final LoginModle loginModle;

    public LoginPresenter(ILoginView loginView) {
        this.iLoginView = loginView;
        this.loginModle = new LoginModle();
    }

    public void getUrl(String url,String uname,String passWord){
        loginModle.getLoginUrl(url,uname,passWord);
        loginModle.setOnfinish(this);
    }



    @Override
    public void Loginfinish(LoginBean loginBean) {
        iLoginView.getState(loginBean);
    }
}
