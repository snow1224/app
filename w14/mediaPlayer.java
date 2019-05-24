package com.example.user.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btPause,btStop;
    ToggleButton btReat;
    SeekBar sbSeek;
    MediaPlayer mPlayer;
    Handler handler = new Handler();
    int duration;
    UpdateSB sbUpdater = new UpdateSB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPause = (ImageButton) findViewById(R.id.btPause);
        btStop = (ImageButton) findViewById(R.id.btStop);
        btReat = (ToggleButton) findViewById(R.id.btRepeat);
        sbSeek = (SeekBar) findViewById(R.id.sbSeek);

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mPlayer.isPlaying()) {
                        mPlayer.pause();
                        btPause.setImageResource(android.R.drawable.ic_media_play);
                        handler.removeCallbacks(sbUpdater);
                    } else {
                        mPlayer.start();
                        btPause.setImageResource(android.R.drawable.ic_media_pause);
                        handler.postDelayed(sbUpdater, 1000);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mPlayer.isPlaying()){
                        mPlayer.stop();
                        mPlayer.prepare();
                        btPause.setImageResource(android.R.drawable.ic_media_play);
                        handler.removeCallbacks(sbUpdater);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        sbSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try{
                    mPlayer.seekTo(progress);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayer = new MediaPlayer();
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.music);
        try{
            mPlayer.setDataSource(this , uri);
            mPlayer.prepare();
            duration = mPlayer.getDuration();
            sbSeek.setMax(duration);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    class UpdateSB extends Thread{
        public void run(){
            if(mPlayer.isPlaying()){
                int sec = mPlayer.getCurrentPosition();
                sbSeek.setProgress(sec);
            }
            handler.postDelayed(this,1000);
        }
    }
}

