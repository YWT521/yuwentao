package com.baway.yuwentao.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baway.yuwentao.R;
import com.baway.yuwentao.mondel.HomeBean;
import com.baway.yuwentao.mondel.utils.Util;
import com.baway.yuwentao.present.HomePresent;
import com.baway.yuwentao.sqlite.SqliteDao;
import com.baway.yuwentao.view.adapter.XlistViewAdapter;
import com.baway.yuwentao.view.iview.IHomeView;
import com.limxing.xlistview.view.XListView;

import java.util.List;

/**
 * 类用途 :主页面
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 8:40
 */
public class MainActivity extends AppCompatActivity implements IHomeView<HomeBean>, XListView.IXListViewListener {

    private XListView xListView;
    private HomePresent homePresent;
    private XlistViewAdapter adapter;
    private boolean refre = true;
    private SqliteDao dao;
    private List list;
    //    private int pageCount=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean b = Util.isNetWorkAvial(this);
        if (b) {

        } else {
            list = dao.queryDB();

        }
        initData();

    }


    private void initData() {
        xListView = (XListView) findViewById(R.id.xlist_view);
        homePresent = new HomePresent();
        homePresent.attachView(this);
        adapter = new XlistViewAdapter(MainActivity.this);
        xListView.setAdapter(adapter);
        homePresent.getDataForServer(HomeBean.class);

    }

    @Override
    public void callBack(HomeBean homeBean) {
        Log.e("----------------",homeBean+"");
        adapter.setData(homeBean);
        adapter.addMore(homeBean.getData(), refre);
        adapter.notifyDataSetChanged();
        dao = new SqliteDao(this);
        for (int i = 0; i < homeBean.getData().size(); i++) {
            dao.insert(homeBean.getData().get(i).getIMAGEURL(), homeBean.getData().get(i).getTITLE());
        }

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
