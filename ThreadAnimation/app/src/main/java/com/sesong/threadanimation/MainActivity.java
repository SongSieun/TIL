package com.sesong.threadanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GView gView = new GView(this);     // SurfaceView 생성
        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(gView);            // 설정해 놓은 frameLayout에  SurfaceView를 추가
    }

    public class GView extends SurfaceView implements SurfaceHolder.Callback {
        public Ball basket[] = new Ball[10];
        private GThread thread;

        public GView(Context context) {    // SurfaceView 생성자
            super(context);
            SurfaceHolder holder = getHolder();
            holder.addCallback(this);
            thread = new GThread(holder);  // 그리기 Thread 생성
            for (int i = 0; i < 10; i++)              // Ball 생성
                basket[i] = new Ball(20);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.setRunning(true);
            thread.start();                    // 그리기 Thread 시작
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.setRunning(false);   // 그리기 Thread 종료
            while (retry) {
                try {
                    thread.join();              // 그리기 Thread 종료 대기
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GThread extends Thread {
        private boolean mRun = false;
        private SurfaceHolder mSurfaceHolder;

        // 생성자  : surfaceView holder 연결
        public GThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
        }

        @Override
        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    c.drawColor(Color.BLACK);   // 배경 그리기
                    synchronized (mSurfaceHolder) {
                        for (Ball b : basket)           // Ball 그리기
                            b.paint(c);
                    }
                } finally {
                    if (c != null)
                        mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }

        public void setRunning(boolean b) {
            mRun = b;
        }
    }

    class Ball {
        int x, y, xInc = 1, yInc = 1;
        int diameter;
        int WIDTH = 1080, HEIGHT = 1920;

        public Ball(int d) {
            this.diameter = d;
            x = (int) (Math.random() * (WIDTH - d) + 3);
            y = (int) (Math.random() * (HEIGHT - d) + 3);
            xInc = (int) (Math.random() * 30 + 1);
            yInc = (int) (Math.random() * 30 + 1);
        }

        // Ball의 위치 정하기와 해당위치에서 그리기
        public void paint(Canvas g) {
            Paint paint = new Paint();
            if (x < diameter || x > (WIDTH - 300))
                xInc = -xInc;     // 좌우 경계에서 방향 전환
            if (y < diameter || y > (HEIGHT - 300))
                yInc = -yInc;     // 상하 경계에서 방향 전환
            x += xInc;
            y += yInc;
            paint.setColor(Color.RED);
            g.drawCircle(x, y, diameter, paint);
        }
    }
}