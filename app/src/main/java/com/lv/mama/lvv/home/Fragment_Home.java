package com.lv.mama.lvv.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.HomeBean;
import com.lv.mama.lvv.home.adapter.XRAdapter;
import com.lv.mama.lvv.home.presenter.HomePresenter;
import com.lv.mama.lvv.home.view.IHomeView;
import com.lv.mama.lvv.utils.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 卢总 on 2017/11/9.
 */

public class Fragment_Home extends Fragment implements IHomeView {


    Unbinder unbinder;
    @BindView(R.id.home_xrcv)
    XRecyclerView homeXrcv;
    private LinearLayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unbinder = ButterKnife.bind(this, view);
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.setUrl(Api.TYPE_HOME);
        //加布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);



        return view;
    }


    @Override
    public void getData(HomeBean.DataBean data) {
        homeXrcv.setLayoutManager(layoutManager);
        XRAdapter adapter = new XRAdapter(data, getActivity());
        homeXrcv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
