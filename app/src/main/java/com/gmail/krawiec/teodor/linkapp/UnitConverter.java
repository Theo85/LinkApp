package com.gmail.krawiec.teodor.linkapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UnitConverter extends AppCompatActivity {

    EditText editFrom, editTo;
    Button unit1Button, unit2Button, catButton;
    int unit1 = -1, unit2 = -1, category = 0;
    CharSequence[] elementsUnits;
    CharSequence[] categories = {"Temperature","Distance","Currency","Speed"};
    CharSequence[] array1 = {"Celsius","Fahrenheit","Kelvin"};
    CharSequence[] array2 = {"Meter","Kilometer","Mile","Foot","Inch"};
    CharSequence[] array3 = {"PLN","EUR","USD","CHF","GBP"};
    CharSequence[] array4 = {"km/h","miles/h","m/s",};
    CharSequence[][] units = {array1,array2,array3,array4};
    double[] temperatures = {};
    double[] currencies = {1, 4.18404, 3.0284, 3.42889, 5.08499};
    double[] distances = {1, 1000, 1609.344, 0.3048, 0.0254};
    double[] speeds = {1, 1.609344, 3.6};
    double[][] converters= {temperatures, distances, currencies, speeds};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);
        editFrom = (EditText)findViewById(R.id.editText1);
        editTo = (EditText)findViewById(R.id.editText2);
        unit1Button = (Button)findViewById(R.id.button1);
        unit2Button = (Button)findViewById(R.id.button2);
        catButton = (Button)findViewById(R.id.button4);
    }

    public void change1Unit(View view) {
        elementsUnits = units[category];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose category");
        builder.setItems(elementsUnits, new DialogInterface.OnClickListener(){
           public void onClick(DialogInterface dialog, int item){
               unit1Button.setText(elementsUnits[item]);
               unit1 = item;
           }
        });
        builder.create();
        builder.show();
    }

    public void change2Unit(View view) {
        elementsUnits = units[category];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose category");
        builder.setItems(elementsUnits, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int item){
                unit2Button.setText(elementsUnits[item]);
                unit2 = item;
            }
        });
        builder.create();
        builder.show();
    }

    public void changeCategory(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose category");
        builder.setItems(categories, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int item){
                catButton.setText(categories[item]);
                category = item;
                unit1 = -1;
                unit2 = -1;
                unit1Button.setText("Select Unit");
                unit2Button.setText("Select Unit");
            }
        });
        builder.create();
        builder.show();
    }

    public void count(View view) {
        if(editFrom.getText().toString().isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "No data given";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            double from = Double.parseDouble(editFrom.getText().toString());
            if (category == -1 || unit1 == -1 || unit2 == -1){
                Context context = getApplicationContext();
                CharSequence text = "Pick units and category";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
            }else{
                if (category == 0){
                    switch (unit1){
                        case 1:
                            from = 5 / 9 * (from - 32);
                            break;

                        case 2:
                            from = from - 273.15;
                            break;
                    }
                    switch (unit2){
                        case 1:
                            from = 32 + 9 / 5 * from;
                            break;

                        case 2:
                            from = from + 273.15;
                            break;
                    }
                } else {
                    from = from * converters[category][unit1];
                    from = from / converters[category][unit2];
                }
                editTo.setText(String.valueOf(from));
            }
        }
    }

    public void pasteData (View view){
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        ClipData.Item item = clipData.getItemAt(0);
        String text = item.getText().toString();
        editFrom.setText(text);
        //allows to paste any string from clipboard including letters
    }
    public void copyData (View view){
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Converter Result",editTo.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
    }
}
