package com.dorasima.shareforall.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBClass extends SQLiteOpenHelper {
    public DBClass(@Nullable Context context) {
        super(context, "login_data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql =  "create table login_data_table("
                + "idx integer primary key autoincrement, "
                + "nickname text not null, "
                + "email text not null, "
                + "password text not null, "
                + "phonenumver text not null, "
                + "age date not null"
                + ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
