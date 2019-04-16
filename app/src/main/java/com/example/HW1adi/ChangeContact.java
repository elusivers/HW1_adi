package com.example.HW1adi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChangeContact extends AppCompatActivity {
    String SetName;
    String[] table;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button cancel;
    boolean check_press = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact);
        table = getResources().getStringArray(R.array.contacts);
        rb1 = findViewById(R.id.radiobutton_1);
        rb1.setText(table[0]);
        rb2 = findViewById(R.id.radiobutton_2);
        rb2.setText(table[1]);
        rb3 = findViewById(R.id.radiobutton_3);
        rb3.setText(table[2]);
        rb4 = findViewById(R.id.radiobutton_4);
        rb4.setText(table[3]);
        rb5 = findViewById(R.id.radiobutton_5);
        rb5.setText(table[4]);

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void confirm(View view) {
        if(check_press) {
            Intent intent = new Intent();
            intent.putExtra("SetName", SetName);
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Select contact", Toast.LENGTH_LONG).show();
        }
    }
    public void click_Name(View view) {

        check_press = ((RadioButton) view).isChecked();

        if(check_press) {
            switch (view.getId()) {
                case R.id.radiobutton_1:
                    SetName = table[0];
                    break;
                case R.id.radiobutton_2:
                    SetName =  table[1];
                    break;
                case R.id.radiobutton_3:
                    SetName =  table[2];
                    break;
                case R.id.radiobutton_4:
                    SetName =  table[3];
                    break;
                case R.id.radiobutton_5:
                    SetName =  table[4];
                    break;
            }
        }
    }
}
