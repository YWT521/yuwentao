package com.baway.yuwentao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baway.yuwentao.mondel.bean.Bean;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 类用途 :
 * 作者 : 郁文涛
 * 时间 : 2017/5/15 11:07
 */

public class SqliteDao {
    private Context context;
    private final SQLiteDatabase db;

    public SqliteDao(Context context) {
        SqliteOpenHen openHen = new SqliteOpenHen(context);
        db = openHen.getWritableDatabase();
    }



    public void insert(String image, String name) {
        ContentValues values = new ContentValues();
        values.put("image", image);
        values.put("namekey", name);
        db.insert("utils", null, values);
        db.close();
    }
    public List<Bean> queryDB(){
        List<Bean> list=new ArrayList<>();
        Cursor query = db.query("utils", null, null, null, null, null, null);
        while (query.moveToNext()){
            String image = query.getString(query.getColumnIndex("image"));
            String namekey = query.getString(query.getColumnIndex("namekey"));
            Bean bean = new Bean();
            bean.setImagename(image);
            bean.setName(namekey);
            list.add(bean);
        }
        return list;
    }
}
