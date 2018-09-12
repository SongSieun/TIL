package com.sesong.graphicexam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SingleTouchView penView;
    private Path mPath;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        penView = new SingleTouchView(this);
        setContentView(penView);
    }

    // 그림 영역 View 클래스 정의
    public class SingleTouchView extends View {
        public SingleTouchView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(4);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            // 사각형의 좌상, 우하 좌표
            // canvas.drawRect(100, 100, 200, 200, mPaint);
            // 원의 중심 x, y, 지름
            canvas.drawCircle(300, 250, 80, mPaint);
            canvas.drawCircle(400, 400, 300, mPaint);
            // 타원의 왼쪽 Top, 오른쪽 Bottom
            canvas.drawOval(380, 350, 420, 500, mPaint);

            // path 자취 만들기
            mPath = new Path();

            mPath.moveTo(450, 300);
            mPath.lineTo(450 + 50, 300 - 100);
            mPath.lineTo(450 + 100, 300);
            canvas.drawPath(mPath, mPaint);

            mPaint.setColor(Color.RED);
            mPath.moveTo(300, 600); // 자취  시작점 이동
            mPath.lineTo(300+50, 600-50); // 자취 직선
            mPath.lineTo(300+100, 600); // 자취 직선
            mPath.lineTo(300+150, 600-50); // 자취 직선
            mPath.lineTo(300+200, 600); // 자취 직선
            canvas.drawPath(mPath, mPaint);
        }
    }
}
