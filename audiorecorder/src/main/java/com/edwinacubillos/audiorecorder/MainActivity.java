package com.edwinacubillos.audiorecorder;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button bGrabar, bReproducir, bDetener;
    MediaRecorder mediaRecorder;
    File archivo;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bGrabar = findViewById(R.id.bGrabar);
        bReproducir = findViewById(R.id.bReproducir);
        bDetener = findViewById(R.id.bDetener);
    }

    @SuppressLint("NewApi")
    public void grabarClicked(View view) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        File path = new File(Environment.getExternalStorageDirectory().getPath());

        try {
            archivo = File.createTempFile("temporal",".3gp",path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.setOutputFile(archivo.getAbsolutePath());

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
        bGrabar.setEnabled(false);
        bDetener.setEnabled(true);
        bReproducir.setEnabled(false);

    }

    public void detenerClicked(View view) {
        mediaRecorder.stop();
        mediaRecorder.release();

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bDetener.setEnabled(false);
                bGrabar.setEnabled(true);
                bReproducir.setEnabled(true);
            }
        });

        bDetener.setEnabled(false);
        bGrabar.setEnabled(true);
        bReproducir.setEnabled(true);


    }

    public void reproducirClicked(View view) {
        mediaPlayer.start();
        bDetener.setEnabled(false);
        bGrabar.setEnabled(false);
        bReproducir.setEnabled(false);
    }
}
