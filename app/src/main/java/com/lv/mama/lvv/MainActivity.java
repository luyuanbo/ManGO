package com.lv.mama.lvv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hjm.bottomtabbar.BottomTabBar;
import com.lv.mama.lvv.cart.Fragment_Cart;
import com.lv.mama.lvv.home.Fragment_Home;
import com.lv.mama.lvv.mine.Fragment_Mine;
import com.lv.mama.lvv.mine.view.LoginActivity;
import com.lv.mama.lvv.sort.Fragment_sort;
import com.lv.mama.lvv.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(35, 35)
                .setFontSize(10)
                .setChangeColor(Color.RED, Color.GRAY)
                //前面第一个是点击后的图片，第二个是点击前的图片
                .addTabItem("首页", R.mipmap.ic_nav_home_press, R.mipmap.ic_nav_home, Fragment_Home.class)
                .addTabItem("分类", R.mipmap.ic_nav_class_press, R.mipmap.ic_nav_class, Fragment_sort.class)
                .addTabItem("购物车", R.mipmap.ic_nav_cart_press, R.mipmap.ic_nav_cart, Fragment_Cart.class)
                .addTabItem("个人", R.mipmap.ic_nav_user_press, R.mipmap.ic_nav_user, Fragment_Mine.class)
                .isShowDivider(false);
        bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                 if (position==2||position==3){
                     boolean state = SPUtils.contains(MainActivity.this, "state");
                     if (!state){
                         startActivity(new Intent(MainActivity.this, LoginActivity.class));
                     }

                 }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.clear(this);
    }
}
