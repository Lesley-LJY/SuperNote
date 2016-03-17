package com.lijunyan.tjut.text_2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    Button button1, button2,button3,button4,button5,button6,button7,button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.camera);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, CameraNext.class);
                startActivity(intent1);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 1);
            }
        });

        button2 = (Button) findViewById(R.id.voice);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Voice.class);
                startActivity(intent2);
            }
        });

        button3 = (Button) findViewById(R.id.note);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Note.class);
                startActivity(intent3);
            }
        });

        button4 = (Button) findViewById(R.id.blackboard);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, Blackboard.class);
                startActivity(intent4);
            }
        });

        button5 = (Button) findViewById(R.id.clock);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, Clock.class);
                startActivity(intent5);
            }
        });

        button6 = (Button) findViewById(R.id.search);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(MainActivity.this, Search.class);
                startActivity(intent6);
            }
        });

        button7 = (Button) findViewById(R.id.share);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(MainActivity.this, Share.class);
                startActivity(intent7);
            }
        });

        button8 = (Button) findViewById(R.id.other);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(MainActivity.this, Other.class);
                startActivity(intent8);
            }
        });

    }

}