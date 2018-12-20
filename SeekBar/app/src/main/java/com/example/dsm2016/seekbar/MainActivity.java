package com.example.dsm2016.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.textview);
        final SeekBar sb = (SeekBar) findViewById(R.id.seekbar);

        tv.setText(String.valueOf(sb.getProgress()));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv.setText("트래킹 시작");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setText("트래킹 종료");
            }
        });
    }
}
