package com.lv.mama.lvv.sort.model;

import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.sort.bean.DatarightBean;
import com.lv.mama.lvv.sort.bean.DateGridBean;
import com.lv.mama.lvv.utils.RetroFactorySort;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 卢总 on 2017/11/13.
 */

public class SortModle implements ISortMode {

    private List<DataleftBean.DatasBean.ClassListBean> class_list;

    private List<DatarightBean.DatasBean.ClassListBean> class_right;
    private List<DateGridBean.DatasBean.ClassListBean> gridList;
    public onfinish onfinish;

    public interface onfinish{
        void onfinishleft(List<DataleftBean.DatasBean.ClassListBean> class_list);
        void onFinishRight(List<DatarightBean.DatasBean.ClassListBean> class_right);
        void onFinishGrid(List<DateGridBean.DatasBean.ClassListBean> gridList);
    }

    public void setOnfinish(SortModle.onfinish onfinish) {
        this.onfinish = onfinish;
    }

    /**
     * 请求分类左边数据
     * @param url
     */
    @Override
    public void getUrl(String url) {
        Observable<DataleftBean> sortLeftBean = RetroFactorySort.getInstance().getSortLeftBean(url);
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
                        class_list = dataleftBean.getDatas().getClass_list();
                        onfinish.onfinishleft(class_list);
                    }
                });
    }

    /**
     * 请求分类右边数据
     * @param url
     */
    @Override
    public void getRigthUrl(String url,String gc_id) {
        Observable<DatarightBean> sortRight = RetroFactorySort.getInstance().getSortRight(url,gc_id);
        sortRight.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DatarightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DatarightBean datarightBean) {
                        class_right = datarightBean.getDatas().getClass_list();
                        onfinish.onFinishRight(class_right);

                    }
                });
    }

    /**
     * 请求适配器里的数据
     * @param url
     * @param gc_name
     */
    @Override
    public void getAbapterUrl(String url, String gc_id,String gc_name) {

        Observable<DateGridBean> sortRight = RetroFactorySort.getInstance().getSortGrid(url,gc_id,gc_name);
        sortRight.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateGridBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DateGridBean dateGridBean) {
                        gridList = dateGridBean.getDatas().getClass_list();
                        onfinish.onFinishGrid(gridList);
                    }
                });
    }


}
