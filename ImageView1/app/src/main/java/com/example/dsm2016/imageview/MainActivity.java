package com.example.dsm2016.imageview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int mDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDegree = mDegree + 90;
                imageView = (ImageView) findViewById(R.id.image);
                imageView.setImageBitmap(rotateImage(BitmapFactory.decodeResource(getResources(), R.drawable.eye), mDegree));
            }
        });

        ImageView img = (ImageView)findViewById(R.id.image);
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, android.R.drawable.btn_star_big_on);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 200, 90, false);
        img.setImageBitmap(bitmap1);
    }
    public  Bitmap rotateImage(Bitmap src, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }
}
