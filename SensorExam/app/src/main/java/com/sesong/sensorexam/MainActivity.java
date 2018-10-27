package com.sesong.sensorexam;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    public int mSensorCount = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 센서 서비스를 얻어오고 조도센서와 연결
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    protected void onResume() {
        super.onResume();
        // 센서를 센서 매니저에 등록
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        // 등록된 센서를 unregister
        sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            mSensorCount++;
            String str;

            // 조도 센서의 값은 event.values[0]에 있음
            str = "Brightness Sensor Value : " + event.values[0] + "lux";
            Log.d("Sensor Data ", str);

            if (event.values[0] < 5){

            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
