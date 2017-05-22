package com.baway.yuwentao.present;

import com.baway.yuwentao.view.iview.IMvpView;

/**
 * 类用途 :主的present解析层
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 9:17
 */

public class BasePresent<T extends IMvpView> {
    private T mt;

    public void attachView(T t) {
        this.mt = t;
    }

    public T getMt() {
        return mt;
    }
}
