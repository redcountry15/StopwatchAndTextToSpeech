package com.example.stopwatchandtexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stopwatch extends AppCompatActivity {
TextView tv;
Button mulai,pause,reset,lap;
Handler handler;
long milisekonTime,StartTime,TimeBuff,UpdateTime = 0L;
int milisekon,sekon,menit;
List<String> Arrlist;
ListView List;
String [] ListElement = new String[]{};
ArrayAdapter<String> Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        // TextView StopWatch
        tv = findViewById(R.id.TextViewStopwatch);
        //Button
        mulai = findViewById(R.id.btnMulai);
        pause = findViewById(R.id.btnBerhenti);
        reset = findViewById(R.id.btnReset);
        lap = findViewById(R.id.btnSimpan);
        List = findViewById(R.id.Lv1);

        handler = new Handler();
        Arrlist = new ArrayList<String>(Arrays.asList(ListElement));

        Adapter = new ArrayAdapter<String>(Stopwatch.this,android.R.layout.simple_list_item_1
            ,Arrlist);
        List.setAdapter(Adapter);




        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                reset.setEnabled(false);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += milisekonTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                milisekonTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                sekon = 0;
                menit =0;
                milisekon =0;

                tv.setText("00:00:00");
                Arrlist.clear();
                Adapter.notifyDataSetChanged();
            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Arrlist.add(tv.getText().toString());
                Adapter.notifyDataSetChanged();
            }
        });




    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            milisekonTime  = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + milisekonTime;
            sekon = (int) (UpdateTime/100);
            menit =sekon/60;
            sekon =  sekon%60;

            milisekon = (int) (UpdateTime%100);
            tv.setText("" + menit + ":"+ String.format("%02d", sekon) + ":"+ String.format("%03d", milisekon));

            handler.postDelayed(this,0);
        }
    };
}
