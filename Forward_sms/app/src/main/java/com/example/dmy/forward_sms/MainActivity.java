package com.example.dmy.forward_sms;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {



    private EditText mNumText;
    private EditText mRuleText;
    private EditText mRuleNumText;
//    private RadioGroup mRadioGroup_or_and;
    private RadioButton mRadioButton_or;
    private RadioButton mRadioButton_and;
    private Boolean or_and=true;
    private Switch mBtnLanJie;
    private Switch mBtnZhuanfa;
    private Button mRuleBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mainActivity=this;

//++++++++==========================================================================================
        //获取短信权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        0 );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS},
                        0 );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        0 );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



//+++++++++++++++++++++==================================================================+++++++++++++++++++++++++


        mNumText = (EditText)findViewById(R.id.numText);
        mRuleText = (EditText)findViewById(R.id.ruleText);
        mRuleNumText = (EditText)findViewById(R.id.ruleNumText);
//        mRadioGroup_or_and = (RadioGroup)findViewById(R.id.BtnGroup_or_and);
        mRadioButton_or = (RadioButton)findViewById(R.id.radioBtn_or);
        mRadioButton_and = (RadioButton)findViewById(R.id.radioBtn_and);
//        mTextView=(EditText)findViewById(R.id.textView);
        mBtnLanJie = (Switch)findViewById(R.id.lanjie);
        mBtnZhuanfa = (Switch)findViewById(R.id.zhuanfa);
        mRuleBtn = (Button)findViewById(R.id.rule_btn);


        //显示输入内容
        SharedPreferences preferences=getSharedPreferences("data", Context.MODE_PRIVATE);
        String phoneNum = preferences.getString("num","");
        String rule = preferences.getString("rule","");
        String ruleNum = preferences.getString("ruleNum","");
        Boolean or_and = preferences.getBoolean("or_and",true);                 //true 为 or false 为 and



        mNumText.setText(phoneNum);
        mRuleText.setText(rule);
        mRuleNumText.setText(ruleNum);
        if(or_and)
            mRadioButton_or.setChecked(true);
        else
            mRadioButton_and.setChecked(true);


        mBtnLanJie.setChecked(preferences.getBoolean("lanjie",true));
        mBtnZhuanfa.setChecked(preferences.getBoolean("zhuanfa",true));

    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu ){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
}


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_rubbish_sms:

                Intent smsActivityintent = new Intent(this,smsActivity.class);
                startActivity(smsActivityintent);

                Log.d("menu_btn","sms");

                return true;

            case R.id.menu_teach:
                Intent help = new Intent(this,helpActivity.class);
                startActivity(help);
                Log.d("menu_btn","teach");


                return true;


            case R.id.menu_about:
                Intent about = new Intent(this,aboutActivity.class);
                startActivity(about);

                Log.d("menu_btn","about");

                return true;


            default:
                return super.onOptionsItemSelected(item);


        }



    }




    public void clickButton(View view){
        switch (view.getId()){
            case R.id.btnQuery:
                saveData();
                break;
            case R.id.radioBtn_or:
                or_and=true;
                break;
            case R.id.radioBtn_and:
                or_and=false;
                break;
            case R.id.rule_btn:
                mRuleNumText.setText(R.string.NumTextRule);
                mRuleText.setText(R.string.RuleTextRule);
                mRadioButton_and.setChecked(true);
                mRadioButton_or.setChecked(false);
                or_and = false;
                saveData();
                break;
            case R.id.lanjie:
                saveData();
                Log.d("debug_btn","lanjie");
                break;
            case R.id.zhuanfa:
                saveData();
                Log.d("debug_btn","zhuanfa");

                break;

            default:
                break;
        }
    }


    public void saveData(){
        try{
            String phoneNum = mNumText.getText().toString();
            String rule = mRuleText.getText().toString();
            String ruleNum = mRuleNumText.getText().toString();

            Boolean lanjie = mBtnLanJie.isChecked();
            Boolean zhuanfa = mBtnZhuanfa.isChecked();



            if(!phoneNum.matches("[1][1-9][\\d]{9}")) {
//                mNumText.setHintTextColor("red");

                mNumText.setError("请输入正确的号码");
                mNumText.requestFocus();


            }
            else{



            SharedPreferences sharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("num",phoneNum);
            editor.putString("rule",rule);
            editor.putString("ruleNum",ruleNum);
            editor.putBoolean("or_and",or_and);
            editor.putBoolean("lanjie",lanjie);
            editor.putBoolean("zhuanfa",zhuanfa);
            editor.commit();
            Log.d("save","save success");
            Toast.makeText(MainActivity.this,"保存成功！！",Toast.LENGTH_LONG);
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"请输入！！",Toast.LENGTH_LONG);
            Log.d("save","save fail");
        }


    }



}