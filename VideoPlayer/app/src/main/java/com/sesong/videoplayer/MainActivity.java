package com.sesong.videoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.edit);
        Button button = (Button) findViewById(R.id.startBtn);

        final VideoView videoView = (VideoView) findViewById(R.id.vv);
        MediaController mediaController = new MediaController(this);
        mediaController.setVisibility(View.VISIBLE);
        videoView.setMediaController(mediaController);

//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.advideo));
//        videoView.start();
//        String uri = "http://www.ithinknext.com/mydata/board/files/F201308021823010.mp4";
//        videoView.setVideoPath(uri);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = editText.getText().toString();
                if (uri != null) {
                    videoView.setVideoPath(uri);
                }
            }
        });
    }
}
