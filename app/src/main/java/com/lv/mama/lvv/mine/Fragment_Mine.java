package com.lv.mama.lvv.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.StateBean;
import com.lv.mama.lvv.mine.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by 卢总 on 2017/11/9.
 */

public class Fragment_Mine extends Fragment {

    @BindView(R.id.login)
    TextView login;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        //注册事件
        EventBus.getDefault().register(this);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void ononMoonStickyEvent(StateBean stateBean){
        login.setText(stateBean.getUname());

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.login)
    public void onViewClicked() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
