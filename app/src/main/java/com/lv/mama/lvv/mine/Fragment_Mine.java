package com.lv.mama.lvv.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.mama.lvv.R;
import com.lv.mama.lvv.bean.StateBean;
import com.lv.mama.lvv.mine.view.LoginActivity;
import com.lv.mama.lvv.utils.SPUtils;

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
    private String uname;

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
        uname = stateBean.getUname();
        login.setText(uname);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        String state= login.getText().toString().trim();
        Log.d("state++++++", "onViewClicked: "+state);
        if (state==uname){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            //设置对话框图标，可以使用自己的图片，Android本身也提供了一些图标供我们使用
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            //设置对话框标题
            builder.setTitle("提示框?");
            //设置对话框内的文本
            builder.setMessage("您确定退出登碌吗！");
            //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 执行点击确定按钮的业务逻辑
                    login.setText("未登录");
                    SPUtils.clear(getContext());
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 执行点击取消按钮的业务逻辑
                }
            });
            //使用builder创建出对话框对象
            AlertDialog dialog = builder.create();
            //显示对话框
            dialog.show();

        }else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
}
