package com.prabakaran_g.corporatemonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class GameOverActivity extends AppCompatActivity {

    private Button StartGameAgain;
    private Button win;


    private TextView DisplayScore;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        AdView adView = findViewById(R.id.adView);
        MobileAds.initialize(this,"ca-app-pub-9096560946069188~2957830868");

        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);






        score = getIntent().getExtras().get("score").toString();

        StartGameAgain = (Button)findViewById(R.id.play_again_btn);
        win = (Button)findViewById(R.id.winbtn);

        DisplayScore = (TextView)findViewById(R.id.displayscore);

        win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent win=new Intent(GameOverActivity.this,priceActivity.class);
                startActivity(win);
            }
        });


        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);


            }
        });


        DisplayScore.setText("Score "+score);


    }
}
