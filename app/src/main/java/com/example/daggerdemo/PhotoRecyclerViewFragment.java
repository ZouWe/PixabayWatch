package com.example.daggerdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerViewAdapter;
import entity.ImageInfo;
import entity.RequestInfo;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoRecyclerViewFragment extends Fragment {
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<ImageInfo> imageInfoList;
    private String q="nature";
    private String imageType="photo";
    private String category="nature";
    private int page=1;
    private int perPage=20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_recyclerview,container,false);
        init(view);
        getParams();
        getImage(q,imageType,category,page,perPage);
        return view;
    }

    private void getParams(){
        Bundle bundle=getArguments();
        imageType=bundle.getString("imageType");

    }

    private void init(View view){
        smartRefreshLayout=view.findViewById(R.id.smart_refresh_layout);
        recyclerView=view.findViewById(R.id.recycler_view);
        imageInfoList=new ArrayList<>();
        recyclerViewAdapter=new RecyclerViewAdapter(imageInfoList, getChildFragmentManager());
        recyclerView.setAdapter(recyclerViewAdapter);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getImage(q,imageType,category,page,perPage);
                refreshlayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                getImage(q,imageType,category,page,perPage);
                refreshlayout.isLoading();
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    private void getImage(String q,String imageType,String category,int page,int perPage){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageAPI imageAPI=retrofit.create(ImageAPI.class);
        imageAPI.getRequestInfo("12457864-f64a46b7c641e7f9ed1da81ef",q,"zh",imageType,category,page,perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RequestInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestInfo requestInfo) {
                        imageInfoList.addAll(requestInfo.getHits());
                        recyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
