package com.baway.yuwentao.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yuwentao.R;
import com.baway.yuwentao.mondel.HomeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.ArrayList;
import java.util.List;

/**
 * 类用途 :适配器
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 9:29
 */

public class XlistViewAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBean.DataBean> list = new ArrayList<>();
    private final DisplayImageOptions options;
    public XlistViewAdapter(Context context) {
        this.context = context;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否内存缓存
                .cacheOnDisk(true)//是否sdcard缓存
                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_xlist, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(getItem(position).getTITLE());
        ImageLoader.getInstance().displayImage(getItem(position).getIMAGEURL(), holder.imageView, options);
        return convertView;
    }

    public void setData(HomeBean homeBean) {
        if (homeBean != null) {
            list.addAll(homeBean.getData());
        }
    }


    public void addMore(List<HomeBean.DataBean> homes, boolean refre) {
        for (HomeBean.DataBean dataBean : homes) {
            if (refre) {
                list.add(0, dataBean);
            } else {
                list.add(dataBean);
            }
        }
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
