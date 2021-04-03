package com.dorasima.shareforall.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.dorasima.shareforall.data.DBTable.*;

public class DBClass extends SQLiteOpenHelper {
    public DBClass(@Nullable Context context) {
        super(context, TABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql =  "create table if not exists "+TABLE+" ("
                + INDEX+" integer primary key autoincrement, "
                + NICKNAME+" text not null, "
                + EMAIL+" text not null, "
                + PASSWORD+" text not null, "
                + PHONE_NUMBER+" text not null, "
                + AGE+" integer not null, "
                + DATE+" date not null"
                + ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
