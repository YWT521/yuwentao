package com.baway.yuwentao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类用途 :
 * 作者 : 郁文涛
 * 时间 : 2017/5/15 10:55
 */

public class SqliteOpenHen extends SQLiteOpenHelper {

    public SqliteOpenHen(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table utils(_id integer primary key autoincrement,image varchar (30),namekey carchar (30))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
