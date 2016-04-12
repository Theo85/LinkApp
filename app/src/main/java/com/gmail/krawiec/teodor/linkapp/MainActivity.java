package com.gmail.krawiec.teodor.linkapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 11) {
            getSupportActionBar().hide();
        }
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.short_sound);
        mediaPlayer.start();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(".MainMenuActivity");
                    startActivity(intent);
                }
            }
        };
        thread.start();

    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }

    public void openBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(intent);
        System.exit(0);
    }
}
