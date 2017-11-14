package com.lv.mama.lvv.sort.presenter;

import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.sort.model.SortModle;
import com.lv.mama.lvv.sort.model.SortModle.onfinish;
import com.lv.mama.lvv.sort.view.ISortView;

import java.util.List;


/**
 * Created by 卢总 on 2017/11/14.
 */

public class SortPresenter implements onfinish{

    private final ISortView iSortView;
    private final SortModle sortModle;

    public SortPresenter(ISortView iSortView) {
        this.iSortView = iSortView;
        this.sortModle = new SortModle();
        sortModle.setOnfinish(this);
    }

    public void setSortLeftUrl(String url){

        sortModle.getUrl(url);
    }
    @Override
    public void onfinishleft(List<DataleftBean.DatasBean.ClassListBean> class_list) {
        iSortView.getleft(class_list);
    }
}
