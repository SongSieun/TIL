package com.example.dsm2016.imageview;

import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.ImageView);

        Button button = (Button)findViewById(R.id.btnScaleCenter);
        Button button1 = (Button)findViewById(R.id.btnScaleFitCenter);
        Button button2 = (Button)findViewById(R.id.btnScaleFitEnd);
        Button button3 = (Button)findViewById(R.id.btnScaleFitStart);
        Button button4 = (Button)findViewById(R.id.btnScaleFitXY);
        Button button5 = (Button)findViewById(R.id.btnScaleMatrix);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnScaleCenter:
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;

            case R.id.btnScaleFitCenter:
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;

            case R.id.btnScaleFitEnd:
                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;

            case R.id.btnScaleFitStart:
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;

            case R.id.btnScaleFitXY:
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;

            case R.id.btnScaleMatrix:
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                break;
        }
    }
}
