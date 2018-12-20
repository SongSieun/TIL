package com.example.dsm2016.zoomcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;

public class MainActivity extends AppCompatActivity {
    private float scale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ZoomControls zc = (ZoomControls)findViewById(R.id.ZoomControl);
        final TextView tvContents = (TextView)findViewById(R.id.Contents);


        zc.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale += 0.1;
                if (scale > 4){
                    scale = 4;
                }
                tvContents.setScaleX(scale);
                tvContents.setScaleY(scale);
                tvContents.setText(String.valueOf(scale));
            }
        });
        zc.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale -= 0.1;
                if (scale <= 1){
                    scale = 1;
                }
                tvContents.setScaleX(scale);
                tvContents.setScaleY(scale);
                tvContents.setText(String.valueOf(scale));
            }
        });
        zc.setZoomSpeed(1000);
    }
}
