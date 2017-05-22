package com.baway.yuwentao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baway.yuwentao.mondel.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类用途 :sqliteDao类
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 10:19
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
        values.put("IMAGEURL", image);
        values.put("TITLE", name);
        db.insert("user", null, values);
        db.close();
    }
    public List queryDB(){
        List list=new ArrayList<>();
        Cursor query = db.query("user", null, null, null, null, null, null);
        while (query.moveToNext()){
            String image = query.getString(query.getColumnIndex("IMAGEURL"));
            String namekey = query.getString(query.getColumnIndex("TITLE"));
            Bean bean = new Bean();
            bean.setImage(image);
            bean.setTitle(namekey);
            list.add(bean);
        }
        return list;
    }
}
