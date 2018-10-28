package com.sesong.screenfilterexam;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

public class MyService extends Service {
    private View view;
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;

    @Override
    public void onCreate() {
        view = new MyLoadView(this);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(view, params);
        super.onCreate();
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class MyLoadView extends View {
        private Paint paint;

        public MyLoadView(Context context) {
            super(context);
            paint = new Paint();
            paint.setTextSize(100);
            paint.setARGB(200, 10, 10, 10);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawARGB(100, 255, 212, 0);
            canvas.drawText(getString(R.string.app_name), 10, 100, paint);
        }
    }
}
