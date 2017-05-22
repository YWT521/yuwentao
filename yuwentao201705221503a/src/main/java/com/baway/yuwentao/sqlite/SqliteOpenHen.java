package com.baway.yuwentao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类用途 :创建数据库类
 * 作者 : 郁文涛
 * 时间 : 2017/5/22 10:17
 */

public class SqliteOpenHen extends SQLiteOpenHelper {

    public SqliteOpenHen(Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id integer primary key autoincrement,IMAGEURL varchar (30),TITLE carchar (30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
