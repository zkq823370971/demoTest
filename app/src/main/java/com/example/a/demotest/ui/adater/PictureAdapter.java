package com.example.a.demotest.ui.adater;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.a.demotest.bean.Picture;
import com.example.a.demotest.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Picture> mPictureList;
    private Context mContext;
    private RequestManager requestManager;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ImageView picture_Image;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        picture_Image = view.findViewById(R.id.image1);
        holder.pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Picture picture = mPictureList.get(position);
                if (picture_Image.getVisibility() == View.GONE) {
                    picture_Image.setVisibility(View.VISIBLE);
                }
                Toast.makeText(view.getContext(), "clicked" + picture.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picture picture = mPictureList.get(position);
        //holder.picture_Image.setImageResource(picture.getImageId());
        holder.pictureName.setText(picture.getName());
        requestManager.load(picture.getImageId()).into(holder.picture_Image);
    }

    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture_Image;
        TextView pictureName;
        View pictureView;

        public ViewHolder(View view) {
            super(view);
            pictureView = view;
            pictureName = (TextView) view.findViewById(R.id.name);
            picture_Image = view.findViewById(R.id.image1);
        }
    }

    public PictureAdapter(List<Picture> pictureList, Context context) {
        mPictureList = pictureList;
        mContext = context;
        requestManager = Glide.with(mContext);
    }
}
