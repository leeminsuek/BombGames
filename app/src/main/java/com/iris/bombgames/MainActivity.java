package com.iris.bombgames;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.iris.bombgames.activity.BombActivity;
import com.iris.bombgames.activity.LaddersActivity;
import com.iris.bombgames.view.BombView;
import com.iris.bombgames.view.MaterialDialog;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bombBtn = (Button) findViewById(R.id.bomb_btn);
        Button laddersBtn = (Button) findViewById(R.id.ladders_btn);

        bombBtn.setTag(BombActivity.class);
        laddersBtn.setTag(LaddersActivity.class);

        bombBtn.setOnClickListener(mGameSelectBtnListener);
        laddersBtn.setOnClickListener(mGameSelectBtnListener);
    }

    /***
     * 폭탄게임 버튼 리스너
     */
    private View.OnClickListener mGameSelectBtnListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class tag = (Class)v.getTag();
            startActivity(new Intent(getApplicationContext(), tag));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
