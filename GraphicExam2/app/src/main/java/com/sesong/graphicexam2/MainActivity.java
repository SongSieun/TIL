package com.sesong.graphicexam2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    SingleTouchView penView;
    private Path path;
    private Paint paint;
    int curShape = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        penView = new SingleTouchView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.main_frame);
        frameLayout.addView(penView);
    }

    public class SingleTouchView extends View {
        int startX = -1, stopX = -1, startY = -1, stopY = -1;

        public SingleTouchView(Context context){
            super(context);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            switch (curShape) {
                case 1:
                    paint.setColor(Color.RED);
                    canvas.drawRect(startX, startY, stopX, stopY, paint);
                    break;
                case 2:
                    paint.setColor(Color.BLUE);
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            return false;
        }
    }
}
