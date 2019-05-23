package adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.daggerdemo.DialogImageViewFragment;
import com.example.daggerdemo.R;

import java.io.Serializable;
import java.util.List;

import activity.MainActivity;
import entity.ImageInfo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ImageInfo> imageInfos;
    private int len;
    private Context mContext;
    private FragmentManager fragmentManager;
    private int screenWidth = 0;
//    private String imagePreviewUrl;
//    private ImageView imageViewBigPhoto;
    private static String TAG="RecyclerViewAdapter";

    public RecyclerViewAdapter(List<ImageInfo> imageInfos,FragmentManager fragmentManager) {
        this.imageInfos = imageInfos;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
            screenWidth = getScreenWidth();
        }
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);
        Log.i(TAG,imageInfos.size()+"");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final String imagePreviewUrl=imageInfos.get(i).getWebformatURL();
        final int imageHeight=imageInfos.get(i).getPreviewHeight();
        final int imageWidth=imageInfos.get(i).getPreviewWidth();

        final int halfScreem= screenWidth / 2;
//        ViewGroup.LayoutParams params=new LinearLayout.LayoutParams(540,imageHeight*540/imageWidth);
        ViewGroup.LayoutParams params = viewHolder.imageView.getLayoutParams();
        params.width = halfScreem;
        params.height= imageHeight *halfScreem/imageWidth;
        viewHolder.imageView.setLayoutParams(params);
        Log.i(TAG,imagePreviewUrl);
        Glide.with(mContext).load(imagePreviewUrl).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogImageViewFragment dialogImageViewFragment=new DialogImageViewFragment();
                Bundle bundle=new Bundle();
                bundle.putSerializable("imageInfos",(Serializable) imageInfos);
                bundle.putInt("position",i);
                dialogImageViewFragment.setArguments(bundle);
                dialogImageViewFragment.show(fragmentManager,"dia");

//                Dialog dialog=new Dialog(mContext);
//                dialog.setView(R.layout.diag_imageview);
//                dialog.setContentView(R.layout.diag_imageview);
//                imageViewBigPhoto = dialog.findViewById(R.id.big_imageview);
//                Glide.with(mContext)
//                        .load(imagePreviewUrl)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .into(imageViewBigPhoto);
//                Log.i(TAG,imagePreviewUrl);
////                 imageViewBigPhoto.setImageResource(R.mipmap.ic_launcher);
//                dialog.setCanceledOnTouchOutside(true); // Sets whether this dialog is
//                Window w = dialog.getWindow();
//                WindowManager.LayoutParams lp = w.getAttributes();
//                lp.x = 0;
//                lp.y = 40;
//                dialog.onWindowAttributesChanged(lp);
//                dialog.show();

//                View rootView = View.inflate(mContext, R.layout.diag_imageview, null);
//                ImageView imageViewBigPhoto = rootView.findViewById(R.id.big_imageview);
//                ViewGroup.LayoutParams params = imageViewBigPhoto.getLayoutParams();
//                params.height=imageHeight * screenWidth /imageWidth;
//                imageViewBigPhoto.setLayoutParams(params);
//                Glide.with(mContext)
//                        .load(imagePreviewUrl)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .into(imageViewBigPhoto);
//                AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
//                        .setView(rootView);
//                builder.show();

            }
        });
    }

    //得到屏幕宽度
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    public int getItemCount() {
        len=imageInfos.size();
        return len;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_view);
            imageView=itemView.findViewById(R.id.image_view);
        }
    }
}
