package com.example.dmy.calculator;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    private TextView mTextView;
    private TextView mTextView_C;
    private TextView mTextView_jiajian;
    private TextView mTextView_baifenhao;
    private TextView mTextView_chu;
    private TextView mTextView_7;
    private TextView mTextView_8;
    private TextView mTextView_9;
    private TextView mTextView_4;
    private TextView mTextView_5;
    private TextView mTextView_6;
    private TextView mTextView_jian;
    private TextView mTextView_cheng;
    private TextView mTextView_1;
    private TextView mTextView_2;
    private TextView mTextView_3;
    private TextView mTextView_jia;
    private TextView mTextView_0;
    private TextView mTextView_dian;
    private TextView mTextView_deng;
//    private TextView mTextView_C;
//    private TextView mTextView_C;

    private String textView="";

    private float num_x;
    private float num_y;
    private float num_result;
    private String sign="";
    static Activity mainActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivity = this;

        Intent ServiceIntent = new Intent(this,MyIntentService.class);
        startService(ServiceIntent);


        mTextView = (TextView)findViewById(R.id.textView);
        mTextView_0 = (TextView)findViewById(R.id.num_0);
        mTextView_1 = (TextView)findViewById(R.id.num_1);
        mTextView_2 = (TextView)findViewById(R.id.num_2);
        mTextView_3 = (TextView)findViewById(R.id.num_3);
        mTextView_4 = (TextView)findViewById(R.id.num_4);
        mTextView_5 = (TextView)findViewById(R.id.num_5);
        mTextView_6 = (TextView)findViewById(R.id.num_6);
        mTextView_7 = (TextView)findViewById(R.id.num_7);
        mTextView_8 = (TextView)findViewById(R.id.num_8);
        mTextView_9 = (TextView)findViewById(R.id.num_9);
        mTextView_C = (TextView)findViewById(R.id.btn_C);
        mTextView_jiajian = (TextView)findViewById(R.id.btn_jiajian);
        mTextView_baifenhao = (TextView)findViewById(R.id.btn_baifenhao);
        mTextView_chu = (TextView)findViewById(R.id.btn_chu);
        mTextView_cheng = (TextView)findViewById(R.id.btn_cheng);
        mTextView_jian = (TextView)findViewById(R.id.btn_jian);
        mTextView_jia = (TextView)findViewById(R.id.btn_jia);
        mTextView_deng = (TextView)findViewById(R.id.btn_deng);
        mTextView_dian = (TextView)findViewById(R.id.btn_dian);


        Intent intent=getIntent();
        if(intent.getStringExtra("history")!=null){
            textView=intent.getStringExtra("history");
            mTextView.setText(textView);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu ){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                 //这个是设置菜单的监听事件

        switch (item.getItemId()){
            case R.id.history_btn:
                Intent intent = new Intent(this,historyActivity.class);


                startActivity(intent);
//                Log.d("debug","menu_btn_add has clicked");
//                this.finish();
                return true;



            default:
                return super.onOptionsItemSelected(item);

        }

    }




    public void onClick(View v) {


        switch (v.getId()){
            case R.id.num_0:
                textView=textView+"0";
                mTextView.setText(textView);
                Log.d("debug_onclick","num_0");

                break;
            case R.id.num_1:
                textView=textView+"1";
                mTextView.setText(textView);
                break;
            case R.id.num_2:
                textView=textView+"2";
                mTextView.setText(textView);
                break;
            case R.id.num_3:
                textView=textView+"3";
                mTextView.setText(textView);

                break;
            case R.id.num_4:
                textView=textView+"4";
                mTextView.setText(textView);

                break;
            case R.id.num_5:
                textView=textView+"5";
                mTextView.setText(textView);

                break;
            case R.id.num_6:
                textView=textView+"6";
                mTextView.setText(textView);

                break;
            case R.id.num_7:
                textView=textView+"7";
                mTextView.setText(textView);

                break;
            case R.id.num_8:
                textView=textView+"8";
                mTextView.setText(textView);

                break;
            case R.id.num_9:
                textView=textView+"9";
                mTextView.setText(textView);

                break;


            case R.id.btn_jiajian:

                if(textView==""){
                    textView="-";
//                    mTextView.setText(textView);
                }
                else {
                    if (Float.parseFloat(textView) > 0)
                        textView = "-" + textView;
                    else
                        textView = textView.replace("-", "");

                }
                mTextView.setText(textView);
                break;

            case R.id.btn_baifenhao:
                if(textView!="") {
                    textView=String.valueOf(Float.parseFloat(textView)/100);
                }
                mTextView.setText(textView);
                break;

            case R.id.btn_chu:
                num_x=Float.parseFloat(textView);
                mTextView.setText("/");
                textView="";
                sign="/";


                break;

            case R.id.btn_cheng:
                num_x= Float.parseFloat(textView);
                mTextView.setText("x");
                textView="";
                sign="x";

                break;

            case R.id.btn_jian:
                num_x=Float.parseFloat(textView);
                mTextView.setText("-");
                textView="";
                sign="-";
                break;

            case R.id.btn_jia:
                num_x=Float.parseFloat(textView);
                mTextView.setText("+");
                textView="";
                sign="+";

                break;

//            case R.id.btn_deng:
//
//                break;

            case R.id.btn_dian:
                if(textView!=""){
                    if(textView.matches("[\\d]+[.]"))
                        textView=textView.replace(".","");
                    else
                        textView=textView+".";
                }
                break;

//            case R.id.btn_jiajian:
//
//                break;






            case R.id.btn_deng:

                if(sign!="") {
                    num_y = Float.parseFloat(textView);
                    if (sign.matches("[+]"))
                        num_result = num_x + num_y;
                    else if (sign.matches("[-]"))
                        num_result = num_x - num_y;
                    else if (sign.matches("[x]"))
                        num_result = num_x * num_y;
                    else if (sign.matches("[/]"))
                        if (num_y != 0)
                            num_result = num_x / num_y;

                    if (num_y == 0)
                        mTextView.setText("error");
                    else
                        mTextView.setText(String.valueOf(num_result));


                    Log.d("debug_deng", String.valueOf(num_x) + "   " + String.valueOf(num_y) + "   " + String.valueOf(num_result));

                    String history = String.valueOf(num_x)+sign+String.valueOf(num_y)+"="+String.valueOf(num_result);

                    saveHistory(history);


                    textView = "";
                    sign="";
                }
                    break;


            case R.id.btn_C:
                textView="";
                mTextView.setText(textView);
                num_x=0;
                num_y=0;
                sign="";

                break;


        }

    }


    public void saveHistory(String history){
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            history=history+"\r\n";
            outputStream = openFileOutput("history", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(history);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("debug" ,"文件写入失败");

        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }



//public void setTextView(String textView){
//    this.textView=textView;
//}

}
