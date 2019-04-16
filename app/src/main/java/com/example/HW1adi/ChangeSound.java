package com.example.HW1adi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChangeSound extends AppCompatActivity {

    Spinner spiner;
    Button cancel;
    int sound_number=0;
    String[] group={""};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sound);

        cancel = findViewById(R.id.button_cancel);
        spiner = findViewById(R.id.spinner);
        group = getResources().getStringArray(R.array.sounds);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, group);

        spiner.setAdapter(arrayAdapter);

        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int choose, long id) {
                switch(choose){
                    case 0:
                        sound_number = R.raw.ring01;
                        break;
                    case 2:
                        sound_number = R.raw.ring02;
                        break;
                    case 3:
                        sound_number = R.raw.ring03;
                        break;
                    case 4:
                        sound_number = R.raw.ring04;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ok(View view) {
        Intent intent = new Intent();
        intent.putExtra("sound", sound_number);
        setResult(RESULT_OK,intent);
        finish();
    }
}
