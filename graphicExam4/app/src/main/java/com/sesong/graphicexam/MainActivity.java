package com.sesong.graphicexam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SingleTouchView penView;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private int penColor;
    private int penWidth;
    Button redBtn, blackBtn, blueBtn, greenBtn, undoBtn, redoBtn, eraseBtn, pointerBtn;
    RadioGroup rGroup;
    private boolean pointerMode = false;
    private ArrayList<Path> pointers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redBtn = findViewById(R.id.red);
        blackBtn = findViewById(R.id.black);
        blueBtn = findViewById(R.id.blue);
        greenBtn = findViewById(R.id.green);
        rGroup = findViewById(R.id.widList);
        rGroup.check(R.id.wid2);
        pointerBtn = findViewById(R.id.pointer);

        penView = new SingleTouchView(this); // Custom View 생성
        FrameLayout frm_layout = findViewById(R.id.main_frame);
        frm_layout.addView(penView);    // 설정해 놓은 frameLayou에 penView를 추가

        undoBtn = findViewById(R.id.button1);
        undoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickUndo();
            }
        });

        redoBtn = findViewById(R.id.button2);
        redoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickRedo();
            }
        });

        eraseBtn = findViewById(R.id.button3);
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penView.onClickErase();
            }
        });
    }

    public void mOnClicked(View v) {
        switch (v.getId()) {
            case R.id.red:
                penColor = Color.RED;
                break;
            case R.id.black:
                penColor = Color.BLACK;
                break;
            case R.id.blue:
                penColor = Color.BLUE;
                break;
            case R.id.green:
                penColor = Color.GREEN;
                break;
        }
    }

    public void mOnClicked2(View v) {
        switch (rGroup.getCheckedRadioButtonId()) {
            case R.id.wid1:
                penWidth = 2;
                break;
            case R.id.wid2:
                penWidth = 4;
                break;
            case R.id.wid3:
                penWidth = 6;
                break;
            case R.id.wid4:
                penWidth = 10;
                break;
            case R.id.wid5:
                penWidth = 15;
                break;
        }
    }

    public void mOnClicked3(View v) {
        if (!pointerMode) {       // Pointer Mode ON
            pointerMode = true;
            pointerBtn.setBackgroundColor(Color.BLUE);
        } else {                                      // Pointer Mode OFF
            pointerMode = false;
            pointerBtn.setBackgroundColor(Color.RED);
            pointers.clear();
        }
    }     // End of mOnClicked3()

    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();
    private ArrayList<Path> undonePaths = new ArrayList<>();
    private ArrayList<Paint> undonePaints = new ArrayList<>();

    public class SingleTouchView extends View {
        public SingleTouchView(Context context) {
            super(context);
            penColor = Color.BLACK;
            penWidth = 4;
            mCanvas = new Canvas();
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(penColor);
            mPaint.setStrokeWidth(penWidth);
            mPath = new Path();     // 선 궤적 저장
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int i = 0;
            if (pointerMode) {
                if (pointers.size() >= 5)               // 오래된 Sub-Path 지우기
                    pointers.remove(0);

                for (Path p : pointers)
                    canvas.drawPath(p, mPaint);
                canvas.drawPath(mPath, mPaint);
            } else {
                for (Path p : paths) {
                    Paint eachPaint = paints.get(i);
                    canvas.drawPath(p, eachPaint);
                    i = i + 1;
                }
                canvas.drawPath(mPath, mPaint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up(x, y);
                    break;
            }
            invalidate();
            return true;
        }  // End of onTouchEvent()

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 10;

        private void touch_start(float x, float y) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(penColor);
            mPaint.setStrokeWidth(penWidth);
            mPath.reset();
            mPath.moveTo(x, y);      // 시작 점 좌표
            mX = x;
            mY = y;
        }

        private int pcount = 0;

        private void touch_move(float x, float y) {
            // 부드러운 곡선 그리기
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
            if (pointerMode) {                               // Sub-Path 만들기
                if (++pcount == 7) {
                    mPath.lineTo(mX, mY);
                    pointers.add(mPath);
                    mPath = new Path();
                    mPath.reset();
                    mPath.moveTo(mX, mY);
                    pcount = 0;
                }
            }
        }

        private void touch_up(float x, float y) {
            if (pointerMode) {  // 최종 위치 표시 만들기
                mPath.reset();
                mPath.moveTo(x - 30, y);
                mPath.lineTo(x, y);
                pointers.add(mPath);
                mPath = new Path();
            } else {
                mPath.lineTo(mX, mY);
                mCanvas.drawPath(mPath, mPaint);
                paths.add(mPath);
                paints.add(mPaint);
                mPath = new Path();
            }
        }

        public void onClickUndo() {
            if (paths.size() > 0) {
                undonePaths.add(paths.remove(paths.size() - 1));
                undonePaints.add(paints.remove(paints.size() - 1));
                invalidate();
            }
        }

        public void onClickRedo() {
            if (undonePaths.size() > 0) {
                paths.add(undonePaths.remove(undonePaths.size() - 1));
                paints.add(undonePaints.remove(undonePaints.size() - 1));
                invalidate();
            }
        }

        public void onClickErase() {
            while (paths.size() > 0) {
                undonePaths.add(paths.remove(paths.size() - 1));
                undonePaints.add(paints.remove(paints.size() - 1));
                invalidate();
            }
        }
    }
}