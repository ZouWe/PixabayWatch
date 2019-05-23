package com.example.daggerdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import adapter.DialogImageViewAdapter;
import entity.ImageInfo;

public class DialogImageViewFragment extends DialogFragment {
    private ViewPager imageViewPager;
    private DialogImageViewAdapter adapter;
    private List<Fragment>  fragments;
    private int postion;
    private List<ImageInfo> imageInfos;
    private static String TAG = "DialogImageViewFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.diag_imageview,container,false);

        getParams();
        initData();
        init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,1200);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void getParams(){
        Bundle bundle=getArguments();
        postion=bundle.getInt("position");
        imageInfos=(List<ImageInfo>) bundle.getSerializable("imageInfos");
        Log.i(TAG,imageInfos.get(1).getLargeImageURL());
    }

    private void sendParams(ImageInfo imageInfo, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putSerializable("imageInfo",imageInfo);
//        Log.i(TAG,url);
        fragment.setArguments(bundle);
    }

    private void initData(){
        fragments=new ArrayList<>();
        for(ImageInfo imageInfo :imageInfos){
            DiaImageViewItemFragment diaImageViewItemFragment = new DiaImageViewItemFragment();
//            Log.i(TAG,imageInfo.getImageURL());
            sendParams(imageInfo,diaImageViewItemFragment);
            fragments.add(diaImageViewItemFragment);
        }
    }

    private void init(View view){
        imageViewPager=view.findViewById(R.id.image_view_page);
        adapter=new DialogImageViewAdapter(getChildFragmentManager(),fragments);
        imageViewPager.setAdapter(adapter);
        imageViewPager.setCurrentItem(postion, false);
    }
}
