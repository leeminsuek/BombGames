package com.iris.bombgames;

import android.animation.Animator;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.iris.bombgames.view.BombView;
import com.iris.bombgames.view.MaterialDialog;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    private RelativeLayout mLayout;

    private ArrayList<BombView> mBombData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

        final MaterialDialog dialog = new MaterialDialog(this);
        dialog.setMessage("")
                .setTitle("알림")
                .setPositiveButton("확인", new MaterialDialog.OnEditDismissListener() {
                            @Override
                            public void onEditTextDismissListener(int number) {
                                if (number >= 20) {
                                    Toast.makeText(getApplicationContext(), "20명까지만 입력가능합니다.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "폭탄을 생성합니다!", Toast.LENGTH_LONG).show();
                                    initBomb(number);
                                    dialog.dismiss();
                                }
                            }
                        }
                )
                .show();
//        initBomb();
    }

    /**
     * 폭탄 설치
     */
    private void initBomb(int index) {
        mBombData = new ArrayList<BombView>();
        BombView bombView = null;
        int bombIndex = (int) (Math.random() * index * 2) + 0;
        for (int i = 0; i < index * 2; i++) {
            bombView = new BombView(this);
            if (i == (bombIndex)) {
                bombView.setmBombYn(BombView.Bomb.BOMB);
            } else {
                bombView.setmBombYn(BombView.Bomb.NON_BOMB);
            }
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bombView.getmBombHeight(), bombView.getmBombWidth());

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels; // etc...
            int height = dm.heightPixels;

            int widthRandom = (int) (Math.random() * (width - bombView.getmBombWidth())) + 1;
            int heightRandom = (int) (Math.random() * (height - bombView.getmBombHeight())) + 1;

            params.leftMargin = widthRandom;
            params.topMargin = heightRandom;
            params.height = bombView.getmBombHeight();
            params.width = bombView.getmBombHeight();
            bombView.setLayoutParams(params);

            bombView.setOnCallbackListener(new BombView.OnBombCallback() {
                @Override
                public void onBombCallback(BombView view, BombView.Bomb bomb) {
                    switch (bomb) {
                        case BOMB:
                            Toast.makeText(getApplicationContext(), "폭탄!", Toast.LENGTH_LONG).show();
//                            mLayout.removeView(view);
                            for(BombView bombv : mBombData) {
                                bombv.setOnClickListener(null);
                            }
                            removeBomb(view, bomb);
                            break;
                        case NON_BOMB:
                            Toast.makeText(getApplicationContext(), "폭탄아니다!", Toast.LENGTH_LONG).show();
                            removeBomb(view, bomb);
//                            mLayout.removeView(view);
                            break;
                    }
                }
            });

            mBombData.add(bombView);

        }

        for (BombView view : mBombData) {
            mLayout.addView(view);
        }
    }

    /**
     * 폭탄 사라질때 애니메이션
     *
     * @param view
     */

    private void removeBomb(final BombView view, final BombView.Bomb bomb) {
        AnimationSet set = new AnimationSet(true);
        Animation scaleAnimation, alphaAnimation;


        scaleAnimation = new ScaleAnimation(0.8f, 1.5f, 0.8f, 1.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        set.addAnimation(scaleAnimation);

        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        set.addAnimation(alphaAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayout.removeView(view);

                switch (bomb) {
                    case BOMB:
                        RelativeLayout transView = (RelativeLayout) findViewById(R.id.main_trans_view);
                        transView.setVisibility(View.VISIBLE);
                        transView.bringToFront();
                        break;
                    case NON_BOMB:
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(set);
    }
//
//        view.animate()
//                .alpha(0.0f)
//                .scaleX(0.5f)
//                .scaleXBy(1.5f)
//                .scaleY(0.5f)
//                .scaleYBy(1.5f)
//                .setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation)
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {
        mLayout = (RelativeLayout) findViewById(R.id.main_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
