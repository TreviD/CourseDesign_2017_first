package com.example.dmy.forward_sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.action;
import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.text;
import static com.example.dmy.forward_sms.smsActivity.sSmsActivity;

/**
 * Created by dmy on 2017/5/3.
 *
 */

public class SmsReceiver extends BroadcastReceiver {


    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";



    @Override
    public void onReceive(Context context, Intent intent) {


        String msgText = null;
        String senderNumber = null;



        //得到短信匹配规则 以及 联系人数据
        SharedPreferences preferences=context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String phoneNum = preferences.getString("num","");
        String rule = preferences.getString("rule","");
        String ruleNum = preferences.getString("ruleNum","");
        Boolean or_and = preferences.getBoolean("or_and",true);

        Boolean lanjie = preferences.getBoolean("lanjie",true);
        Boolean zhuanfa = preferences.getBoolean("zhuanfa",true);




//        Log.d("test",phoneNum+"   "+rule);


        Log.i(TAG, "Intent recieved: " + intent.getAction());

        SmsMessage msg = null;
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] pdusObj = (Object[])bundle.get("pdus");



            Log.d("debug",String.valueOf(pdusObj.length));

            for(Object p : pdusObj){


                Log.d("sms","getSMSing");
                msg = SmsMessage.createFromPdu( (byte[]) p);

                msgText =msg.getMessageBody(); //得到消息内容
                senderNumber = msg.getOriginatingAddress();//得到发信人号码

                Log.d("smsnum_debug",senderNumber);
                Log.d("smsbody_debug",msgText);


//                String msgT= "Nougat is sweet!123342快递";
//                String abc="\\S*123\\S*";
//                Log.d("smsSend",abc);

                Log.d("smsSend_debug",rule);

                Pattern patternText=Pattern.compile(rule);
                Matcher matcherText=patternText.matcher(msgText);                         //短信内容正则匹配

                Pattern patternNum=Pattern.compile(ruleNum);
                Matcher matcherNum=patternNum.matcher(senderNumber);                        //联系人正则匹配

                Boolean booleanText = matcherText.find();
                Boolean booleanNum = matcherNum.find();

                Log.d("debug",ruleNum);
                Log.d("debug",rule);
                Log.d("debug",phoneNum);

                if(booleanNum)
                    Log.d("debug","Num_ture");
                else
                    Log.d("debug","Num_false");

                if(booleanText)
                    Log.d("debug","Text_true");
                else
                    Log.d("debug","Text_false");

                Boolean result = true;

                if(or_and) {
                        Log.d("debug", "or");




                        if(booleanNum || booleanText){
                            result = true;
                            Log.d("debug", "result_true");
                        }
                        else {
                            result = false;
                            Log.d("debug","result_false");
                        }
                }
                else {
                        Log.d("debug", "and");
                        if(booleanNum && booleanText) {
                            result = true;
                            Log.d("debug", "result_true");
                        } else{
                            result = false;
                            Log.d("debug","result_false");
                        }


                    }
//                if (senderNumber.matches("[1][1-9][\\d]{9}")
//                        ) {
//
//                    Log.d("debug_test","senderNumber_matches");
//                }
//
//                if(msgText.matches("[\\S]*123[\\S]*"))
//                {
//                    Log.d("debug_test","msgText_matches");
//
//                }


                if(result&&zhuanfa){

//                    Log.d("debug", "result_true");
                    Log.d("smsSend"," prepare sending");


//                PendingIntent pi = PendingIntent.getActivities(context,0,null,0);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNum,null,msgText,null,null);    //发短信

                    Log.d("smsSend","send success!!!!");

                }
                else{
//                    Log.d("debug","result_false");
                    Log.d("smsSend","error");
//                    Log.d("smsSend","error");

                }

                if(lanjie&&!result) {
                    abortBroadcast();
                    Log.d("lanjie_debug","拦截成功");
                }



            }





            try{

                Intent intentSaveSmsService = new Intent(context,saveSmsService.class);
                intentSaveSmsService.putExtra("sms_num",senderNumber);
                intentSaveSmsService.putExtra("sms_body",msgText);

                context.startService(intentSaveSmsService);
                Log.d("Debug","start service success");

            }catch (Exception e){
                Log.d("Debug","start service fail");
            }

        }






//重启 smsActivity 页面
        sSmsActivity.finish();

        Intent intent1 = new Intent(context,smsActivity.class);
        context.startActivity(intent1);



    }



}

