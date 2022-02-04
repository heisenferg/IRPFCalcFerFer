package com.example.irpfcalcferfer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Lista extends AppCompatActivity {
    ArrayList<String> miLista = new ArrayList<>();
    ListView listView;
    String salarioBruto;
    String deduccionsTrabajo;
    String deduccionesSS;
    String baseImponible;
    String deduccionesDescendientes;
    String deduccionesEdad;
    String deduccionesFamilia;
    String sTramoE;
    String sTramoD;
    String sTramoC;
    String sTramoB;
    String sTramoA;
    String sTramoEFam, sTramoDFam, sTramoCFam, sTramoBFam, sTramoAFam;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, miLista);
        new task().execute();

        listView = findViewById(R.id.listViewCalculos);


        listView.setAdapter(adapter);

    }

  //

    Double baseImpon = MainActivity.baseImponible;
    Double minimFAmiliar= Double.valueOf(MainActivity.minimoFamiliar);
    Double tramoA=0.0;
    Double tramoB=0.0;
    Double tramoC=0.0;
    Double tramoD=0.0;
    Double tramoE=0.0;
    Double exceso=0.0;

    public void calcularTramosBaseImponible(){
        if (baseImpon>=60000){
            tramoA = baseImpon-60000;
            exceso = tramoA;
            baseImpon-=tramoA;
            tramoA = tramoA*0.45;
        }

        if (baseImpon<=60000 && baseImpon>=35200){
            tramoB = baseImpon-35200;
            baseImpon-=tramoB;
            tramoB = tramoB*0.37;
        }
        if (baseImpon<=35200 && baseImpon>=20200){
            tramoC = baseImpon-20200;
            baseImpon-=tramoC;
            tramoC = tramoC*0.30;
        }
        if (baseImpon<=20200 && baseImpon>=12450){
            tramoD = baseImpon-12450;
            baseImpon-=tramoD;
            tramoD = tramoD*0.24;
        }
        if (baseImpon<=12450){
            tramoE = 12450*0.19;
        }

        totalA = tramoA+tramoB + tramoC + tramoD  +tramoE;

        Log.d("TRAMOS", "A: " + tramoA + "B: " + tramoB +
                "C: " + tramoC + "D: " + tramoD + "E: " +tramoE);
    }


    Double tramoAFam=0.0;
    Double tramoBFam=0.0;
    Double tramoCFam=0.0;
    Double tramoDFam=0.0;
    Double tramoEFam=0.0;
    Double excesoFam=0.0;
    Double totalA;
    Double totalB;

    public void calcularTramosFamiliar(){
        if (minimFAmiliar>=60000){
            tramoAFam = minimFAmiliar-60000;
            excesoFam = tramoAFam;
            minimFAmiliar-=tramoAFam;
            tramoAFam = tramoAFam*0.45;
        }

        if (minimFAmiliar<=60000 && minimFAmiliar>=35200){
            tramoBFam = minimFAmiliar-35200;
            minimFAmiliar-=24800;
            tramoBFam = tramoBFam*0.37;
        }
        if (minimFAmiliar<=35200 && minimFAmiliar>=20200){
            tramoCFam = minimFAmiliar-20200;
            minimFAmiliar-=15000;
            tramoCFam = tramoCFam*0.30;
        }
        if (minimFAmiliar<=20200 && minimFAmiliar>=12450){
            tramoDFam = minimFAmiliar-12450;

            minimFAmiliar-=7750;
            tramoDFam = tramoDFam*0.24;
        }
        if (minimFAmiliar<=12450){
            tramoEFam = 12450*0.19;
        }

        totalB = tramoAFam+tramoBFam + tramoCFam + tramoDFam  +tramoEFam;

        Log.d("TRAMOS", "A: " + tramoAFam + "B: " + tramoBFam +
                "C: " + tramoCFam + "D: " + tramoDFam + "E: " +tramoEFam);
    }


    class task extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {


            calcularTramosBaseImponible();

            salarioBruto = "Salario bruto: " + MainActivity.salario;
            deduccionsTrabajo = "Deducciones por trabajo: " + MainActivity.rendimientoTrabajo;
            deduccionesSS = "Deducciones de la SS: " + MainActivity.baseIRPF;
            baseImponible = "Base imponible: " + MainActivity.baseImponible;
            deduccionesDescendientes = "Deducciones por descendientes: " + MainActivity.eurosPorHijo;
            deduccionesEdad = "Deducciones por edad: " + MainActivity.minimoPersonal;
            deduccionesFamilia = "Mínimo familiar: " + MainActivity.minimoFamiliar;
            miLista.add(salarioBruto);
            miLista.add(deduccionsTrabajo);
            miLista.add(deduccionesSS);
            miLista.add(baseImponible);
            miLista.add("--------------");
            sTramoE = "Primer tramo al 19 % = " + tramoE + " €";
            sTramoD = "Segundo tramo al 24 % = " + tramoD + " €";
            sTramoC = "Tercer tramo al 30 % = " + tramoC + " €";
            sTramoB = "Cuarto tramo al  37 % = " + tramoB + " €";
            sTramoA = exceso +  + tramoA + " €";
            miLista.add(sTramoE);
            miLista.add(sTramoD);
            miLista.add(sTramoC);
            miLista.add(sTramoB);
            miLista.add(sTramoA);

            calcularTramosFamiliar();
            miLista.add(deduccionesDescendientes);
            miLista.add(deduccionesEdad);
            miLista.add(deduccionesFamilia);
            miLista.add("--------------");
            sTramoEFam = "Primer tramo al 19 % = " + tramoEFam + " €";
            sTramoDFam = "Segundo tramo al 24 % = " + tramoDFam + " €";
            sTramoCFam = "Tercer tramo al 30 % = " + tramoCFam + " €";
            sTramoBFam = "Cuarto tramo al  37 % = " + tramoBFam + " €";
            sTramoAFam = excesoFam + " € del último tramo al 19 % = " + tramoAFam + " €";
            miLista.add(sTramoEFam);
            miLista.add(sTramoDFam);
            miLista.add(sTramoCFam);
            miLista.add(sTramoBFam);
            miLista.add(sTramoAFam);

            miLista.add("----------------------------");
            Double pagoTotal = totalA-totalB;
            String total = "TOTAL ("+totalA+") - ("+totalB+") = " + pagoTotal;
            miLista.add(total);



            return null;
        }

        @Override
        protected void onPreExecute() {

        }

    }
}