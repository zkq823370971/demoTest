package com.example.a.demotest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a.demotest.bean.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
}
