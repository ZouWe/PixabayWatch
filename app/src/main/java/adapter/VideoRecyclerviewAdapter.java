package adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.daggerdemo.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import entity.VideoInfo;

public class VideoRecyclerviewAdapter extends RecyclerView.Adapter<VideoRecyclerviewAdapter.ViewHolder> {

    private List<VideoInfo> dataList;
    private Context mContext;
    private int len;
    private OnVideoCompleteListener onVideoCompleteListener;
    private int height;
    private int width;
    private static String TAG = "VideoRecyclerviewAdapter";
    public CompleteStateController mController;

    public VideoRecyclerviewAdapter(Context mContext,List<VideoInfo> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

//    public OnVideoCompleteListener getOnVideoCompleteListener() {
//        return onVideoCompleteListener;
//    }

    public void setOnVideoCompleteListener(OnVideoCompleteListener onVideoCompleteListener) {
        this.onVideoCompleteListener = onVideoCompleteListener;
    }

    public interface OnVideoCompleteListener {
        void onVideoCompleted(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_recyclerview,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        CompleteStateController controller = new CompleteStateController(mContext);
        viewHolder.setController(controller);
        return viewHolder;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.itemView.getVisibility() == View.VISIBLE) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    holder.mVideoPlayer.start();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.bindData(dataList.get(position));
        viewHolder.mController.setOnPlayStateChangeListener(new CompleteStateController.OnPlayStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == NiceVideoPlayer.STATE_COMPLETED && onVideoCompleteListener != null) {
                    onVideoCompleteListener.onVideoCompleted(viewHolder.getAdapterPosition());
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                viewHolder.mVideoPlayer.start();
            }
        });



//        if (viewHolder.itemView.getVisibility() == View.VISIBLE) {
//
//            Log.i(TAG,"测试测试");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    viewHolder.mVideoPlayer.start();
//                }
//            });
//        }

        String[] color = {"#008577","#D81B60","#8B8B7A"};
//        viewHolder.view.setBackgroundColor(Color.parseColor(color[i%3]));
    }


    @Override
    public int getItemCount() {
        len = dataList.size();
        return len;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CompleteStateController mController;
        public NiceVideoPlayer mVideoPlayer;

        public ViewHolder(View itemView) {
            super(itemView);
            mVideoPlayer = itemView.findViewById(R.id.nice_video_player);
            mVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE);
            // 将列表中的每个视频设置为默认16:9的比例
//            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
//            params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
//            params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
//            mVideoPlayer.setLayoutParams(params);
        }

        public void setController(CompleteStateController controller) {
            mController = controller;
            mVideoPlayer.setController(mController);
        }

        public CompleteStateController getController(){
             return mController;
        }

        public void bindData(VideoInfo video) {
            mController.setTitle("");
            mController.setLenght(98000);
            Glide.with(itemView.getContext())
                    .load("https://i.vimeocdn.com/video/"+video.getPicture_id()+"_295x166")
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .centerCrop()
                    .into(mController.imageView());
//            mVideoPlayer.setUp(video.getVideos().getSmall().getUrl(), null);
            mVideoPlayer.setUp("https://bmob-cdn-13634.bmobcloud.com/2019/05/21/429fc31140a44fe6800cc7dacd3fa944.mp4", null);

        }
    }
}
