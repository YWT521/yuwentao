package com.baway.yuwentao.mondel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 类用途 :网络判断类
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 10:08
 */

public class Util {
    public static boolean isNetWorkAvial(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }
}
