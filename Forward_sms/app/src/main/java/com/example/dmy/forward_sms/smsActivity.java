package com.example.dmy.forward_sms;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class smsActivity extends AppCompatActivity {



    private List<SMS> mSmsList = new ArrayList<>();

    public static Activity sSmsActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sSmsActivity = this;

        initSms();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.sms_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        smsAdapter adapter = new smsAdapter(mSmsList);
        recyclerView.setAdapter(adapter);


    }

    private void initSms() {

        try {

            SQLiteDatabase smsDB = new sms_SQLite_storage(this).getWritableDatabase();
            Cursor cursor = smsDB.query("sms_table", null, null, null, null, null, null, null);
            cursor.moveToLast();

//        while(cursor.moveToPrevious()){
//            String sms_num = cursor.getString(cursor.getColumnIndex("sms_num"));
//            String sms_body = cursor.getString(cursor.getColumnIndex("sms_body"));
//            SMS a=new SMS(sms_num,sms_body);
//            mSmsList.add(a);
//        }


            do {
                String sms_num = cursor.getString(cursor.getColumnIndex("sms_num"));
                String sms_body = cursor.getString(cursor.getColumnIndex("sms_body"));
                SMS a = new SMS(sms_num, sms_body);
                mSmsList.add(a);
            } while (cursor.moveToPrevious());



        smsDB.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
