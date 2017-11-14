package com.lv.mama.lvv.sort.view;

import com.lv.mama.lvv.sort.bean.DataleftBean;
import com.lv.mama.lvv.sort.bean.DatarightBean;

import java.util.List;

/**
 * Created by 卢总 on 2017/11/13.
 */

public interface ISortView {
    void getleft(List<DataleftBean.DatasBean.ClassListBean> class_list);
    void getRight(List<DatarightBean.DatasBean.ClassListBean> class_right);
}
