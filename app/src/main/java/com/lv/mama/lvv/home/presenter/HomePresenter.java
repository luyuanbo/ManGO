package com.lv.mama.lvv.home.presenter;


import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.home.model.HomeModel;
import com.lv.mama.lvv.home.view.IHomeView;

/**
 * Created by 卢总 on 2017/11/10.
 */

public class HomePresenter implements HomeModel.Onfinish{

    private final IHomeView iHomeView;
    private final HomeModel homeModel;


    public HomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        this.homeModel=new HomeModel();
        homeModel.setOnfinish(this);
    }
    public void setUrl(String url){

        homeModel.getUrl(url);
    }

    @Override
    public void OnFinishListener(HomeBean.DataBean data) {
       iHomeView.getData(data);
    }
}
