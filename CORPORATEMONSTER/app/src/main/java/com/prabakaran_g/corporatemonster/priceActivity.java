package com.prabakaran_g.corporatemonster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class priceActivity extends AppCompatActivity {

    public Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        b1 = findViewById(R.id.backbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(priceActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
