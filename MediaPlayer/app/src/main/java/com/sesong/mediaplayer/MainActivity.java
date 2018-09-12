package com.sesong.mediaplayer;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Thread w;
    private MediaPlayer mediaPlayer;
    private Button start_Btn, exit_Btn, pause_Btn, restart_Btn, repeat_Btn, reoffBtn;
    private RadioGroup radioGroup;
    private SeekBar progress;
    private TextView playing;
    private int playbackposition = -1;
    private int SelectedSong = -1;
    private int SongPlayed = -1;
    private int nextSong;

    ArrayList<Integer> playlist;
    Timer timer;
    MyRunnable runnable;

    class WorkerThread extends Thread {
        @Override
        public void run() {
            if (mediaPlayer == null) return;
            progress.setMax(mediaPlayer.getDuration());
            // 재생 중인 음악의 전체 재생 시간
            while (mediaPlayer != null) {
                progress.setProgress(mediaPlayer.getCurrentPosition());
                SystemClock.sleep(200);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playing = (TextView) findViewById(R.id.playing);

        progress = (SeekBar) findViewById(R.id.progress);
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        start_Btn = (Button) findViewById(R.id.startBtn);
        start_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer != null) timer.cancel();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    playbackposition = -1;
                    // 이전에 수행되고 있던 Worker Thread 의 종료 제어및 종료 확인
                    if (w != null) {
                        try {
                            w.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (SelectedSong == -1) {
                    Toast.makeText(getApplicationContext(), "음악을 선택하세요", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), SelectedSong);
                }

                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    w = new WorkerThread();
                    w.start();
                    Toast.makeText(getApplicationContext(), "음악재생", Toast.LENGTH_SHORT).show();
                }
            }
        });

        exit_Btn = (Button) findViewById(R.id.exitBtn);
        exit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                if (w != null) {
                    try {
                        w.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }
        });


        pause_Btn = (Button) findViewById(R.id.pauseBtn);
        pause_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playbackposition = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
                Toast.makeText(getApplicationContext(), "중지됨", Toast.LENGTH_SHORT).show();
            }
        });

        restart_Btn = (Button) findViewById(R.id.restartBtn);
        restart_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playbackposition != -1 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playbackposition);
                    Toast.makeText(getApplicationContext(), "재시작됨", Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.gList);

        repeat_Btn = (Button) findViewById(R.id.repeatOnBtn);
        repeat_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    playbackposition = -1;
                    if (w != null) {
                        try {
                            w.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                playing.setText("전체 반복 재생모드");
                playlist = new ArrayList<>();
                playlist.add(R.raw.song1);
                playlist.add(R.raw.song2);
                playlist.add(R.raw.song3);

                timer = new Timer();
                playNext();
                playNextschedule();
            }
        });

        reoffBtn = (Button) findViewById(R.id.repeatOffBtn);
        reoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                }
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    playbackposition = -1;
                    if (w != null) {
                        try {
                            w.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                radioGroup.clearCheck();
                SongPlayed = -1;
                return;
            }
        });
        runnable = new MyRunnable();
    }

    public void playNextschedule() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                playNext();
                playNextschedule();
            }
        }, mediaPlayer.getDuration() + 100);
    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            radioGroup.clearCheck();
            switch (nextSong) {
                case R.raw.song1:
                    radioGroup.check(R.id.song1);
                    break;
                case R.raw.song2:
                    radioGroup.check(R.id.song2);
                    break;
                case R.raw.song3:
                    radioGroup.check(R.id.song3);
                    break;
                default:
                    break;
            }
        }
    }

    public void playNext() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            playbackposition = -1;
            if (w != null) {
                try {
                    w.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        nextSong = playlist.get(++SongPlayed);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), nextSong);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (timer != null) {
                    timer.cancel();
                    timer = new Timer();
                    playNext();
                    playNextschedule();
                }
            }
        });
        // 음악 재생 종료 시점에서 수행
        runOnUiThread(runnable);
        // 재생 진행 상태 모니터링 시작
        w = new WorkerThread();
        w.start();
        // 음악 목록 무한 반복 재생
        if (playlist.size() == SongPlayed + 1)
            SongPlayed = -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void mOnClicked(View view) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.song1:
                SelectedSong = R.raw.song1;
                playing.setText("선택된 음악 : song1.mp3");
                break;
            case R.id.song2:
                SelectedSong = R.raw.song2;
                playing.setText("선택된 음악 : song2.mp3");
                break;
            case R.id.song3:
                SelectedSong = R.raw.song3;
                playing.setText("선택된 음악 : song3.mp3");
                break;
            default:
                SelectedSong = -1;
                break;
        }
    }
}
