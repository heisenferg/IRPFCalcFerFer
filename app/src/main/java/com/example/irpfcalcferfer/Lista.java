package com.example.irpfcalcferfer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Lista extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

calcularTramosBaseImponible();
    }

  //  MainActivity.minimoFamiliar;

    Double baseImpon = MainActivity.baseImponible;
    Double tramoA;
    Double tramoB;
    Double tramoC;
    Double tramoD;
    Double tramoE;

    public void calcularTramosBaseImponible(){
        if (baseImpon>=60000){
            tramoA = baseImpon-60000;
            baseImpon-=tramoA;
            tramoA = tramoA*0.45;
        }

        if (baseImpon<=60000 && baseImpon>=35200){
            tramoB = baseImpon-24800;
            baseImpon-=tramoB;
            tramoB = tramoB*0.37;
        }
        if (baseImpon<=35200 && baseImpon>=20200){
            tramoC = baseImpon-15000;
            baseImpon-=tramoC;
            tramoC = tramoC*0.30;
        }
        if (baseImpon<=20200 && baseImpon>=12450){
            tramoD = baseImpon-7750;
            baseImpon-=tramoD;
            tramoD = tramoD*0.24;
        }
        if (baseImpon<=12450){
            tramoE = baseImpon*0.19;
        }

        Log.d("TRAMOS", "A: " + tramoA + "B: " + tramoB +
                "C: " + tramoC + "D: " + tramoD + "E: " +tramoE);
    }
}