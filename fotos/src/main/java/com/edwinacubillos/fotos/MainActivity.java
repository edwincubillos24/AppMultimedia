package com.edwinacubillos.fotos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

   // ImageView imageView;
    VideoView videoView;
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    imageView = findViewById(R.id.iFoto);
      //  name = Environment.getExternalStorageDirectory()+"/foto.jpg";
        videoView = findViewById(R.id.video);
        name = Environment.getExternalStorageDirectory()+"/video.mp4";
    }

    public void tomarFoto(View view) {
 /*       Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//foto
        Uri salida = Uri.fromFile(new File(name));
        i.putExtra(MediaStore.EXTRA_OUTPUT, salida);
        startActivityForResult(i, 1234);*/


   /*     Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//video
        Uri salida = Uri.fromFile(new File(name));
        i.putExtra(MediaStore.EXTRA_OUTPUT, salida);
        startActivityForResult(i,1234);*/

        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK){

   /*         Bitmap bMap = BitmapFactory.decodeFile(name); //foto
            Matrix mat = new Matrix();
            mat.postRotate(90);
            Bitmap bMapRotate = Bitmap.createBitmap(bMap,0,0,
                    bMap.getWidth(), bMap.getHeight(),mat,true);
            imageView.setImageBitmap(bMapRotate);*/

            Uri uri = data.getData(); //video
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(this));
            videoView.start();

        }



    }
}
