package com.lv.mama.lvv.system;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by 卢总 on 2017/10/11.
 */

public class MyApplication extends Application {

    public static Context mContext;
    public static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        mContext=getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }
}
