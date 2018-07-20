package com.example.a.demotest.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.a.demotest.bean.ActivityCollector;
import com.example.a.demotest.bean.Picture;
import com.example.a.demotest.ui.adater.PictureAdapter;
import com.example.a.demotest.R;

import java.util.ArrayList;
import java.util.List;

public class downloadpicActivity extends BaseActivity {
    private List<Picture> picturesList = new ArrayList<>();
    private PictureAdapter adapter;
    private long afterTime = 0;
    private long[] mHits = new long[2]; // 数组长度代表点击次数
    private static final String TAG = "downloadpicActivity";
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadpic);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PictureAdapter(picturesList, this);
        recyclerView.setAdapter(adapter);


        initPicture();
    }

    private void initPicture() {
        for (int i = 0; i < 1; i++) {
            Picture image1 = new Picture("NO.1", R.drawable.x1);
            picturesList.add(image1);
            Picture image2 = new Picture("NO.2", R.drawable.x2);
            picturesList.add(image2);
            Picture image3 = new Picture("NO.3", R.drawable.x3);
            picturesList.add(image3);
            Picture image4 = new Picture("NO.4", R.drawable.x4);
            picturesList.add(image4);
            Picture image5 = new Picture("NO.5", R.drawable.x5);
            picturesList.add(image5);
            Picture image6 = new Picture("NO.6", R.drawable.x6);
            picturesList.add(image6);
        }
        adapter.notifyDataSetChanged();
    }

    /*
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                //这里重写返回键
                Toast.makeText(downloadpicActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();

            } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 1) {
                finish();
                return true;
            }
            return false;
        }
    */
    @Override
    public void onBackPressed() {
        doubleClickFinish();
        //threeClickFinish();
    }

    // 多次点击返回键操作，高级实现方法
    private void threeClickFinish() {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            finish();
            //Toast.makeText(this, "双击返回按钮", Toast.LENGTH_SHORT).show();
        }

    }

    public static List<Activity> activities = new ArrayList<>();

    // 双击点击操作，低级实现方法
    private void doubleClickFinish() {

        // 1. SystemClock.uptimeMillis(); 从开机到现在的毫秒数（手机睡眠的时间不包括在内）；
        // 2. System.currentTimeMillis(); 从1970年1月1日 UTC到现在的毫秒数；
        long curTime = SystemClock.uptimeMillis();
        long time = curTime - afterTime;

        if (time < 500) {
            if (flag<3){
                flag++;
                Toast.makeText(this, "第"+flag+"次", Toast.LENGTH_SHORT).show();
                afterTime = curTime;
            }
        } else {
            afterTime = curTime;
            flag=0;
            Toast.makeText(this, "第"+(flag+1)+"次", Toast.LENGTH_SHORT).show();
            flag=1;
        }
        if (flag==3)
        {
            ActivityCollector.finishAll();
            flag=0;
        }
    }
}
