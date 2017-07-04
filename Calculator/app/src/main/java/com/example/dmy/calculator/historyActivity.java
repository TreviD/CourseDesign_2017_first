package com.example.dmy.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

import static com.example.dmy.calculator.MainActivity.mainActivity;

public class historyActivity extends AppCompatActivity {



    private List<String> historyList = new ArrayList<>();

    RecyclerView mRecyclerView;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        initHistoryList();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        historyViewAdapter adapter = new historyViewAdapter(historyList);
        adapter.setOnItemClickListener(new historyViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String a = historyList.get(position);

                for(int i=0;i<a.length();i++){
                    if(a.substring(i,i+1).matches("[=]"))
                    {
                        a=a.substring(i+1,a.length());
                        Log.d("debug_history",a);
                        break;
                    }
                }

                Intent intent = new Intent(historyActivity.this,MainActivity.class);
                intent.putExtra("history",a);
                startActivity(intent);
                mainActivity.finish();
                historyActivity.this.finish();
            }
        });

        mRecyclerView.setAdapter(adapter);



    }

    private void initHistoryList() {

        FileInputStream in = null;
        BufferedReader reader = null;
        try{
            int i=0;
            List<String> a = new ArrayList<>();

            in = openFileInput("history");
            reader = new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                a.add(line);
                Log.d("debug_read",line);

                i++;
            }

            while(i>0){

                historyList.add(a.get(i-1));
                i--;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("debug" ,"文件读取失败");
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu ){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                 //这个是设置菜单的监听事件

        switch (item.getItemId()){
            case R.id.clear:
                try {
                    FileOutputStream out = openFileOutput("history", Context.MODE_PRIVATE);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                    writer.write("");
                    Log.d("debug","清空完成");
                    writer.close();
                    historyActivity.this.finish();

                }catch (Exception e){
                    Log.d("debug","清空失败");
                }

                return true;



            default:
                return super.onOptionsItemSelected(item);

        }

    }




}
