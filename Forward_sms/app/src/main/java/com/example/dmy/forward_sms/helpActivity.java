package com.example.dmy.forward_sms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class helpActivity extends AppCompatActivity {

    private TextView mTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        mTextView = (TextView)findViewById(R.id.teach_help);

    }
}
