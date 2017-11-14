package com.lv.mama.lvv.system;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by 卢总 on 2017/10/11.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }


}
