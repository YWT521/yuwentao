package com.baway.yuwentao.view.iview;

/**
 * 类用途 :
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 9:15
 */

public interface IHomeView<T> extends IMvpView {
    void callBack(T t);
}
