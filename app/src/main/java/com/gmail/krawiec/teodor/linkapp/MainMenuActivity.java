package com.gmail.krawiec.teodor.linkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void openUnitsActivity(View view) {
        Intent intent = new Intent(this, UnitConverter.class);
        startActivity(intent);
    }
}
