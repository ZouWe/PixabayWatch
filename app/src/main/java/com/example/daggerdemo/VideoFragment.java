package com.example.daggerdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.VideoRecyclerviewAdapter;
import entity.BaseVideo;
import entity.VideoInfo;
import entity.VideoRequestInfo;
import entity.Videos;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoFragment extends Fragment {

    private static String TAG = "VideoFragment";

    private ImageView imageView;
    private PagerSnapHelper pagerSnapHelper;
    private VideoRecyclerviewAdapter adapter;
    private ArrayList<String> list;
    private RecyclerView recyclerView;

    private String q = "flower";
    private String VideoType = "all";
    private String category = "animals";
    private int page = 1;
    private int perPage = 20;


    private List<String> videoUrlList;
    private int videoInfosPosition;
    private List<VideoInfo> videoInfos;

    private NiceVideoPlayer mNiceVideoPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_video,container,false);
        imageView=view.findViewById(R.id.video_image);
        getVideoInfo();
        init(view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    private void init(View view){
        videoUrlList = new ArrayList<>();
        videoInfos = new ArrayList<>();
        adapter = new VideoRecyclerviewAdapter(getActivity(),videoInfos);
        adapter.setOnVideoCompleteListener(new VideoRecyclerviewAdapter.OnVideoCompleteListener() {
            @Override
            public void onVideoCompleted(int position) {
                if (position + 1 < adapter.getItemCount()) {
                    recyclerView.smoothScrollToPosition(position + 1);
                }
            }
        });
        recyclerView = view.findViewById(R.id.video_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((VideoRecyclerviewAdapter.ViewHolder) holder).mVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
//                        recyclerView.;
//                        Toast.makeText(getContext(),"滑到这里了",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Toast.makeText(getContext(),"onScrolled",Toast.LENGTH_SHORT).show();
            }
        });

        pagerSnapHelper = new PagerSnapHelper(){
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                return super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
            }

            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                return super.findSnapView(layoutManager);
            }
        };

        pagerSnapHelper.attachToRecyclerView(recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }



    private void getVideoInfo(){
        String baseUrl = "https://pixabay.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageAPI imageAPI = retrofit.create(ImageAPI.class);
        imageAPI.getVideoRequestInfo("12457864-f64a46b7c641e7f9ed1da81ef",q,"zh",VideoType,category,page,perPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VideoRequestInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoRequestInfo videoRequestInfo) {
                        Log.i(TAG,videoRequestInfo.getHits().size()+"");
                        videoRequestInfo.getHits().get(0).getVideos().getSmall().setUrl("https://bmob-cdn-13634.bmobcloud.com/2019/05/26/f0d5b300406766ae80ae629120ef46b8.mp4");
                        videoRequestInfo.getHits().get(1).getVideos().getSmall().setUrl("https://bmob-cdn-13634.bmobcloud.com/2019/05/26/b86db86e407797f080fffcc3342dca0a.mp4");
                        videoRequestInfo.getHits().get(2).getVideos().getSmall().setUrl("https://bmob-cdn-13634.bmobcloud.com/2019/05/26/244470fd40c4766d8044d8b93354ed06.mp4");

                        videoInfos.addAll(videoRequestInfo.getHits());
                        Log.i(TAG,videoInfos.size()+"");
                        for(VideoInfo videoInfo : videoInfos){
                            videoUrlList.add(videoInfo.getVideos().getLarge().getUrl());
                            Log.i(TAG,videoInfo.getVideos().getTiny().getUrl());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,videoInfos.size()+"");
                    }
                });

    }


}
