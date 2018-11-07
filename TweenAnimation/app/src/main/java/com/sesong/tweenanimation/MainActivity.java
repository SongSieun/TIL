package com.sesong.tweenanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imgTranslate);

        // ImageVIew에 적용할 동작 제어 xml 파일 로딩
        final Animation animTransRight = AnimationUtils.loadAnimation(this, R.anim.trans_right);
        final Animation animTransLeft = AnimationUtils.loadAnimation(this, R.anim.trans_left);
        final Animation animTransAlpha = AnimationUtils.loadAnimation(this, R.anim.trans_alpha);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        final Animation animScaleSmall = AnimationUtils.loadAnimation(this, R.anim.scale_small);
        final Animation animHybrid = AnimationUtils.loadAnimation(this, R.anim.hybrid);
        final Animation animTodo = AnimationUtils.loadAnimation(this, R.anim.trans_left_diagonal);

        Button btnRight = (Button) findViewById(R.id.btn_right);
        Button btnLeft = (Button) findViewById(R.id.btn_left);
        Button btnAlpha = (Button) findViewById(R.id.btn_alpha);
        Button btnRotate = (Button) findViewById(R.id.btn_rotate);
        Button btnLarge = (Button) findViewById(R.id.btn_large);
        Button btnSmall = (Button) findViewById(R.id.btn_small);
        Button btnHybrid = (Button) findViewById(R.id.btn_hybrid);
        Button btnTodo = (Button) findViewById(R.id.btn_todo);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animTransRight);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animTransLeft);
            }
        });

        btnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animScale);
            }
        });

        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animScaleSmall);
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animRotate);
            }
        });

        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animTransAlpha);
            }
        });

        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animHybrid);
            }
        });
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(animTodo);
            }
        });
    }
}
