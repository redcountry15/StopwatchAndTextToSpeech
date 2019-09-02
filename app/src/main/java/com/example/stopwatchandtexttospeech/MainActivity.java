package com.example.stopwatchandtexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button speech,stopwatch ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speech = findViewById(R.id.Button1);
        stopwatch = findViewById(R.id.Button2);

        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aku = new Intent(MainActivity.this,Stopwatch.class);
                startActivity(aku);
            }
        });

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aku =new Intent(MainActivity.this,SpeechToText.class);
                startActivity(aku);
            }
        });
    }
}
