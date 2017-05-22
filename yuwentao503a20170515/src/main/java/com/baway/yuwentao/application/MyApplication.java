package com.baway.yuwentao.application;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * 类用途 :Application类
 * 类的描述:用来实例xutils
 * 作者 : 郁文涛
 * 时间 : 2017/5/15 9:53
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
