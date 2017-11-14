package com.lv.mama.lvv.sort.presenter;

import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.sort.bean.DatarightBean;
import com.lv.mama.lvv.sort.bean.DateGridBean;
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



    public void setSortRightUrl(String url,String gc_id){
        sortModle.getRigthUrl(url,gc_id);
    }

    public void setGridUrl(String url,String gc_id,String gc_name){
        sortModle.getAbapterUrl(url,gc_id,gc_name);
    }
    public void setSortLeftUrl(String url){

        sortModle.getUrl(url);

    }
    @Override
    public void onfinishleft(List<DataleftBean.DatasBean.ClassListBean> class_list) {
        iSortView.getleft(class_list);
    }

    @Override
    public void onFinishRight(List<DatarightBean.DatasBean.ClassListBean> class_right) {
        iSortView.getRight(class_right);
    }

    @Override
    public void onFinishGrid(List<DateGridBean.DatasBean.ClassListBean> gridList) {

    }


}
