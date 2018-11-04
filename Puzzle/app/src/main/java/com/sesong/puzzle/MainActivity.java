package com.sesong.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    public class MyView extends View {
        int horCnt = 3;                      // 그림 가로 분할 수
        int verCnt = 3;                     // 그림 세로 분할 수
        int width, height;                    // 그림 그릴 영역 크기
        int left, top;                      //  첫째 그림조각의 화면 위치
        int orgW, orgH;
        int picW, picH;                     // 그림 조각의 크기
        int vacancy;                        // 그림이 없는 조각의 위치
        Bitmap imgBack, imgOrg;             // 백그라운드 그림과 원본 그림 이미지
        Bitmap picPiece[] = new Bitmap[horCnt * verCnt];
        Bitmap slot[] = new Bitmap[horCnt * verCnt];    //화면 위치 별 그림 이미지
        Bitmap vacancyPiece;

        public MyView(Context context) {
            super(context);

            Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            // 단말기 화면의 크기 가져오기
            display.getSize(size);
            width = size.x;
            height = size.y - 150;                    // 그림의 높이를 조절
            // Background 그림 이미지 가져와 전체 화면에 맞게 크기 조절
            imgBack = BitmapFactory.decodeResource(context.getResources(), R.drawable.wallpaper);
            imgBack = Bitmap.createScaledBitmap(imgBack, size.x, size.y, true);
            // Original 그림 이미지 가져와 크기 조절
            imgOrg = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);
            imgOrg = Bitmap.createScaledBitmap(imgOrg, width - width / 10, height - height / 10, true);
            orgW = imgOrg.getWidth();
            orgH = imgOrg.getHeight();
            picW = orgW / verCnt;                     // 그림 조각의 폭
            picH = orgH / horCnt;                     // 그림 조각의 높이
            left = (width - orgW) / 2;                // 첫번 째 그림조각을 그릴 위치
            top = (height - orgH) / 5;
            for (int i = 0; i < horCnt; i++) {        // Original 그림을 조각 내기
                for (int j = 0; j < verCnt; j++) {
                    picPiece[i * verCnt + j] = Bitmap.createBitmap(imgOrg, j * picW, i * picH, picW, picH);
                }
            }
            // 그림이 없는 빈 조각 만들기
            picPiece[verCnt * horCnt - 1] = Bitmap.createBitmap(imgOrg, 0, 0, 1, 1);
            vacancyPiece = picPiece[verCnt * horCnt - 1];
            shuffle_picPiece();                                        //  그림 조각들을 랜덤 배치 하기

        }

        public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            canvas.drawBitmap(imgBack, 0, 0, null);                        // Background 그림 그리기
            for (int i = 0; i < horCnt; i++) {                                         // 조각그림 배치하여 그리기
                for (int j = 0; j < verCnt; j++)
                    canvas.drawBitmap(slot[i * verCnt + j], left + j * picW + 2 * j, top + i * picH + 2 * i, paint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();

            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    SwapPicPiece(findSlot((int) x, (int) y)); // 조각 이동
                    invalidate();
            }
            return true;
        }

        public int findSlot(int x, int y) {
            int tmpX = (x - left) / picW;
            int tmpY = (y - top) / picH;
            int slot = tmpY * verCnt + tmpX;
            return slot;
        }

        public void SwapPicPiece(int pickedslot) {
            int left = pickedslot - 1;
            int right = pickedslot + 1;
            int up = pickedslot - verCnt;
            int down = pickedslot + verCnt;
            int tmpX = pickedslot % verCnt;
            int tmpY = pickedslot / horCnt;
            if ((left == vacancy) && (tmpX != 0)) {
                slot[left] = slot[pickedslot];
                slot[pickedslot] = vacancyPiece;
                vacancy = pickedslot;
            }
            if ((right == vacancy) && (tmpX != verCnt - 1)) {
                slot[right] = slot[pickedslot];
                slot[pickedslot] = vacancyPiece;
                vacancy = pickedslot;
            }
            if ((up == vacancy) && (tmpY != 0)) {
                slot[up] = slot[pickedslot];
                slot[pickedslot] = vacancyPiece;
                vacancy = pickedslot;
            }
            if ((down == vacancy) && (tmpY != horCnt - 1)) {
                slot[down] = slot[pickedslot];
                slot[pickedslot] = vacancyPiece;
                vacancy = pickedslot;
            }
        }

        public void shuffle_picPiece() {
            ArrayList<Integer> Intlist = new ArrayList<Integer>();

            for (int i = 0; i < horCnt; i++)
                for (int j = 0; j < verCnt; j++)
                    Intlist.add(i * verCnt + j, i * verCnt + j);

            Random random = new Random();
            for (int i = 0; i < verCnt * horCnt; i++) {
                int index = random.nextInt(verCnt * horCnt - i);
                int element = Intlist.get(index);

                slot[i] = picPiece[element];

                if (element == (verCnt * horCnt - 1))
                    vacancy = i;

                Intlist.remove(index);
            }
        }
    }
}