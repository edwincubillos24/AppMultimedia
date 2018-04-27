package com.edwinacubillos.audioreproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playClicked(View view) {
        mediaPlayer = MediaPlayer.create(this, R.raw.duhast);
        mediaPlayer.start();
    }

    public void stopClicked(View view) {
        mediaPlayer.stop();

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseClicked(View view) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }

    }
}
