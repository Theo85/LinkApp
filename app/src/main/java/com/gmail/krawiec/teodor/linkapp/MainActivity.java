package com.gmail.krawiec.teodor.linkapp;

import android.app.ActionBar;
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
    }

    public void openBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(intent);
    }
}
