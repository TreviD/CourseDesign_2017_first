package com.example.dmy.forward_sms;

/**
 * Created by dmy on 2017/5/20.
 */

public class SMS {

    private String mSms_num;
    private String mSms_body;



    public SMS(String sms_num,String sms_body){
        mSms_body=sms_body;
        mSms_num=sms_num;
    }


    public String getSms_num(){
        return mSms_num;

    }

    public String getSms_body(){
        return mSms_body;
    }

}
