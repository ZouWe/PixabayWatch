package com.example.daggerdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import adapter.PhotoFragmentPageAdapter;

public class PhotoFragment extends Fragment {
    private List<Fragment> fragments;
    private PhotoFragmentPageAdapter photoFragmentPageAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo,container,false);
        initData();
        init(view);
        return view;
    }

    private void init(View view){
        viewPager = view.findViewById(R.id.view_page_type_photo);
        tabLayout = view.findViewById(R.id.tab_diff_type_photo);
        photoFragmentPageAdapter=new PhotoFragmentPageAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(photoFragmentPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData(){
        fragments=new ArrayList<>();
        PhotoRecyclerViewFragment photoRecyclerViewFragment1=new PhotoRecyclerViewFragment();
        sendParams("photo",photoRecyclerViewFragment1);
        fragments.add(photoRecyclerViewFragment1);
        PhotoRecyclerViewFragment photoRecyclerViewFragment2=new PhotoRecyclerViewFragment();
        sendParams("illustration",photoRecyclerViewFragment2);
        fragments.add(photoRecyclerViewFragment2);
        PhotoRecyclerViewFragment photoRecyclerViewFragment3=new PhotoRecyclerViewFragment();
        sendParams("vector",photoRecyclerViewFragment3);
        fragments.add(photoRecyclerViewFragment3);
    }

    private void sendParams(String imageType,Fragment fragment){
        Bundle bundle=new Bundle();
        bundle.putString("imageType",imageType);
        fragment.setArguments(bundle);
    }
}



