package com.iris.bombgames.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iris.bombgames.R;


/**
 * Created by 이민석
 *
 * @since 2015 - 04 - 11
 */
public class BombView extends ImageView implements View.OnClickListener {

    /**
     * 폭탄 여부
     */
    private Bomb mBombYn = Bomb.BOMB;

    private boolean mClickYn = false;

//    private int mBombImage = R.drawable.normal;

    private int mBombWidth = 0, mBombHeight = 0;

    private OnBombCallback mListener;

    public enum Bomb {
        BOMB,
        NON_BOMB;
    }


    /**
     * 폭탄인지 아닌지 콜백리스너
     */
    public interface OnBombCallback {
        public void onBombCallback(BombView view, Bomb bomb);
    }


    public BombView(Context context) {
        super(context);
        init();
    }

    public BombView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BombView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Bomb getmBombYn() {
        return mBombYn;
    }

    public void setmBombYn(Bomb mBombYn) {
        this.mBombYn = mBombYn;
    }

    public void setOnCallbackListener(OnBombCallback listener) {
        mListener = listener;
    }

    /**
     * 이미지뷰 초기화
     */
    private void init() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; // etc...

        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        getLayoutParams().height = width / 3;
        getLayoutParams().width = width / 3;

        mBombHeight = mBombWidth = width/3;

//        setImageResource(mBombImage);

        this.setOnClickListener(this);
    }

    public int getmBombWidth() {
        return mBombWidth;
    }

    public void setmBombWidth(int mBombWidth) {
        this.mBombWidth = mBombWidth;
    }

    public int getmBombHeight() {
        return mBombHeight;
    }

    public void setmBombHeight(int mBombHeight) {
        this.mBombHeight = mBombHeight;
    }

    @Override
    public void onClick(View v) {

        if (mClickYn == false && mListener != null) {
            mClickYn = true;
            mListener.onBombCallback((BombView)v, mBombYn);
        }
    }
}
