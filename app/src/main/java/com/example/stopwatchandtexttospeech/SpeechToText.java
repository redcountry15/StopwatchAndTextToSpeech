package com.example.stopwatchandtexttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        mVoiceInputTv = findViewById(R.id.voiceInput);
        mSpeakBtn = findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startVoiceInput();

            }
        });

    }

    public  void startVoiceInput(){
        Intent suara = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        suara.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        suara.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        suara.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hai Ada Yang Bisa Saya Bantu");

        try{
            startActivityForResult(suara,REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                }
                break;
            }
        }
    }
}
