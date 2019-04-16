package com.example.HW1adi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView text_view;
    ImageView image_view;
    int drawable;
    int rand = 0;
    int sound = 0;
    String name = "";
    MediaPlayer media;
    boolean fab_play= false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text_view = findViewById(R.id.textView);
        image_view = findViewById(R.id.main_image);


        randNum();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (media != null) {
                    media.stop();
                    media = null;
                } else if(fab_play) {
                    media = MediaPlayer.create(getApplicationContext(), sound);
                    media.start();
                }
                else //warunek zeby nie odpalalo play przed wybraniem czegokolwiek
                {
                    Snackbar.make(view, "You didn't choose a sound", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void randNum() {
        Random r = new Random();
        rand = r.nextInt(5);
        choosePicture(rand);
    }

    public void choosePicture(int number) {
        if (number == 0)
            drawable = R.drawable.avatar_1;

        else if (number == 1)
            drawable = R.drawable.avatar_2;

        else if (number==2)
            drawable =R.drawable.avatar_3;


        else if (number==3)
            drawable = R.drawable.avatar_4;

        else
            drawable = R.drawable.avatar_5;


        image_view.setImageResource(drawable);
    }

    public void changeContact(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeContact.class);
        startActivityForResult(intent,1);
    }

    public void changeSound(View view) {
        if(!text_view.getText().equals("")){ //z warunkiem żeby nie mozna bylo wejsc w changeSound przed wybraniem kontaktu
        Intent intent = new Intent(getApplicationContext(), ChangeSound.class);
        startActivityForResult(intent, 2);
            }
            else{
                Snackbar.make(view, "You didn't choose a contact", Snackbar.LENGTH_LONG).show();
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK)
            {
                name = data.getStringExtra("SetName");
                text_view.setText(name);
                randNum();
            }

        if(requestCode == 2 && resultCode == RESULT_OK)
            {
                fab_play=true;
                sound = data.getIntExtra("sound", 0);
            }
    }

}
