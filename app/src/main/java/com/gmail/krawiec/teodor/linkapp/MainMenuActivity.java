package com.gmail.krawiec.teodor.linkapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        if (x==0){
            x++;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, R.string.exit_messege, duration);
            toast.show();
        }else{
            System.exit(0);
        }

        Log.d("my test","onBackPressed Called");
    }


    public void openUnitsActivity(View view) {
        Intent intent = new Intent(this, UnitConverter.class);
        startActivity(intent);
    }

    public void testMethod(MenuItem item) {
        Context context = getApplicationContext();
        CharSequence text = "EUREKA!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
