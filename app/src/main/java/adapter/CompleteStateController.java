package adapter;

import android.content.Context;

import com.xiao.nicevideoplayer.TxVideoPlayerController;

public class CompleteStateController extends TxVideoPlayerController {

    public OnPlayStateChangeListener onPlayStateChangeListener;

    public CompleteStateController(Context context) {
        super(context);
    }

    public void setOnPlayStateChangeListener(OnPlayStateChangeListener onPlayStateChangeListener){
        this.onPlayStateChangeListener = onPlayStateChangeListener;
    }


    public interface OnPlayStateChangeListener{
        void onPlayStateChanged(int playState);
    }

    @Override
    protected void onPlayStateChanged(int playState) {
        super.onPlayStateChanged(playState);
        onPlayStateChangeListener.onPlayStateChanged(playState);
    }
}
