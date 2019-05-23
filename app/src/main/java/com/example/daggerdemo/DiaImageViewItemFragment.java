package com.example.daggerdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import entity.ImageInfo;

public class DiaImageViewItemFragment extends Fragment {
    private ImageInfo imageInfo;
    private ImageView imageView;
    private static String TAG = "DiaImageViewItemFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.diag_imageview_item,container,false);
        getParam();
        init(view);
        return view;
    }

    private void getParam(){
        Bundle bundle = getArguments();
        imageInfo = (ImageInfo) bundle.getSerializable("imageInfo");
    }

    private void init(View view){
        imageView=view.findViewById(R.id.big_imageview);

        Glide.with(getContext())
                .load(imageInfo.getLargeImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
