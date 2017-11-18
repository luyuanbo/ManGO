package com.lv.mama.lvv.cart.presenter;


import com.lv.mama.lvv.cart.Bean.Select;
import com.lv.mama.lvv.cart.model.SelectModel;
import com.lv.mama.lvv.cart.view.ISelectView;

/**
 * Created by chentong on 2017/11/17.
 */

public class SelectPresenter implements SelectModel.Setdate{
    private final ISelectView iSelectView;
    private final SelectModel selectModel;
    public SelectPresenter(ISelectView iSelectView) {
        this.iSelectView = iSelectView;
        this.selectModel = new SelectModel(this);
    }
    public void getdate(String uid){
        selectModel.GetCartDate(uid);

    }
    @Override
    public void getCartdate(Select cartGoods) {
        iSelectView.GetCartDate(cartGoods);
    }
}
