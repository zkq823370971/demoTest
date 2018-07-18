package com.example.a.demotest.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.a.demotest.bean.Picture;
import com.example.a.demotest.ui.adater.PictureAdapter;
import com.example.a.demotest.R;

import java.util.ArrayList;
import java.util.List;

public class downloadpicActivity extends AppCompatActivity {
    private List<Picture> picturesList = new ArrayList<>();
    private PictureAdapter adapter;

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
}
