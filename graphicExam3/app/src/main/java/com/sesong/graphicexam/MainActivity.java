package com.sesong.graphicexam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {
    SingleTouchView penView;
    private Path mPath;
    private Paint mPaint;
    int curShape = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        penView = new SingleTouchView(this);     // Custom View 생성
        FrameLayout frm_layout = (FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(penView);                  // 설정해 놓은 frameLayout에  penView를 추가
    }


    // canvas에 그리기 커스텀 뷰
    private class SingleTouchView extends View {
        int startX = -1, stopX = -1, startY = -1, stopY = -1;

        public SingleTouchView(Context context) {
            super(context);

            mPaint = new Paint();                   // 화면에 그려줄 도구(Paint) 객체 생성
            mPaint.setAntiAlias(true);              // 곡선 그릴 때, 계단 형태를 완화
            mPaint.setStyle(Paint.Style.STROKE);    // 외곽 선만 그림
            mPaint.setStrokeWidth(4);               // 선의 굵기
        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (MyShape p : Shapelist) {            // 정보 저장 리스트에 있는 모든 도형 그리기
                switch (p.Stype) {
                    case 1:
                        mPaint.setColor(Color.RED);
                        canvas.drawRect(p.startX, p.startY, p.stopX, p.stopY, mPaint);
                        break;
                    case 2:
                        mPaint.setColor(Color.BLUE);
                        int radius = (int) Math.sqrt(Math.pow(p.stopX - p.startX, 2) + Math.pow(p.stopY - p.startY, 2));
                        canvas.drawCircle(p.startX, p.startY, radius, mPaint);
                        break;
                }
            }
        }

        public MyShape shapeobj;

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:      // 손가락으로 화면을 눌렀을 때
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    shapeobj = new MyShape();                                                 // 도형 정보 저장 객체 생성
                    shapeobj.startX = startX;
                    shapeobj.startY = startY;            // 도형 정보 저장
                    shapeobj.Stype = curShape;
                    break;
                case MotionEvent.ACTION_MOVE:        // 터치 후 손가락을 움직일 때
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    shapeobj.stopX = stopX;
                    shapeobj.stopY = stopY;
                    break;
                case MotionEvent.ACTION_UP:             // 손가락을 화면에서 뗄 때
                    Shapelist.add(shapeobj);                  // 정보 저장 리스트에 연결
                    invalidate();
                    break;
            }
            return true;
        }

        public void onErase() {
            Shapelist.clear();     // 리스트 초기화
            invalidate();
        }
    }

    public void mOnClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                curShape = 1;
                break;
            case R.id.button2:
                curShape = 2;
                break;
            case R.id.button3:
                penView.onErase();
                break;
            default:
                break;
        }

    }

    private ArrayList<MyShape> Shapelist = new ArrayList<MyShape>();   //도형 정보 객체 리스트

    private class MyShape {     // 도형 정보 저장 공간
        int Stype;
        int startX;
        int startY;
        int stopX;
        int stopY;
    }
}
