package com.example.dsm2016.progressbar_loading;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv = (ImageView)findViewById(R.id.image);
        final ProgressBar pb = (ProgressBar)findViewById(R.id.progressBar);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.drawable.dog);
            }
        }, 3000);
    }
}
