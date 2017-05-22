package com.baway.yuwentao.present;

import com.baway.yuwentao.mondel.utils.Utils;
import com.baway.yuwentao.view.iview.IHomeView;

/**
 * 类用途 :解析数据层
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 9:17
 */

public class HomePresent extends BasePresent<IHomeView> {

    private String url="http://www.93.gov.cn/93app/data.do?channelId=0&startNum=3";
    public <T>void getDataForServer(Class<T> t) {
        Utils.getData(url, new Utils.CallBackHome<T>() {
            @Override
            public void callBack(T t) {
                getMt().callBack(t);
            }
        },t);
    }
}
