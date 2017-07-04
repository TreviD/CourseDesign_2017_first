package com.example.dmy.forward_sms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dmy on 2017/5/19.
 */

public class sms_SQLite_storage extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "sms_db.db";
    public static final String table_name = "sms_table";



    public sms_SQLite_storage(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table " + table_name + "(sms_num integer,sms_body text)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
