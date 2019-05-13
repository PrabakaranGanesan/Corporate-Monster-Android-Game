package com.prabakaran_g.corporatemonster;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    MediaPlayer bgmusic;


    private  Corporateview gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







       gameView = new Corporateview(this);
         setContentView(gameView);

        bgmusic = MediaPlayer.create(MainActivity.this,R.raw.gametheme);
        bgmusic.setLooping(true);
        bgmusic.start();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {

                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        gameView.invalidate();

                    }
                });

            }
        },0,Interval);


    }

    @Override
    protected void onPause()
    {
        super.onPause();
        bgmusic.release();
        finish();
    }



}
