package com.lv.mama.lvv.sort.view;


import com.lv.mama.lvv.sort.bean.KindBean;
import com.lv.mama.lvv.sort.bean.RightBean;

import java.util.List;

/**
 * Created by admin on 2017/11/14.
 */

public interface Kview {
    void getleft(List<KindBean.DataBean> data);
    void getright(List<RightBean.DataBean> data);
}
