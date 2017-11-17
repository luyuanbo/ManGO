package com.lv.mama.lvv.sort.presenter;


import com.lv.mama.lvv.sort.bean.KindBean;
import com.lv.mama.lvv.sort.bean.RightBean;
import com.lv.mama.lvv.sort.model.Kmodel;
import com.lv.mama.lvv.sort.view.Kview;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/11/14.
 */

public class Kpresent implements Kmodel.onFinshkind,Kmodel.onRightkind {
    private final Kmodel kmodel;
    private final Kview kview;

    public Kpresent( Kview kview) {
        this.kmodel = new Kmodel();
        this.kview = kview;
    }
    public void getpleft(String str){
        kmodel.Setkind(this);
        kmodel.getKind(str);
    }
    public void getpright(Map<String,String> map,String str){
        kmodel.SetRightkind(this);
        kmodel.getKindrig(map,str);
    }

    @Override
    public void Finshkind(List<KindBean.DataBean> data) {
        kview.getleft(data);
    }

    @Override
    public void Rigthkind(List<RightBean.DataBean> data) {
        kview.getright(data);
    }
}
