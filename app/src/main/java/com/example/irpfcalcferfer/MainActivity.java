package com.example.irpfcalcferfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView fechaNacimiento;
    TextView nHijos;
    TextView salarioBruto;
    Button botonFecha;
    Button botonCalcular;

    public static Double baseIRPF;
    public static final int rendimientoTrabajo=2000;
    public static Double salario;
    public static Double baseImponible;
    public static int numeroHijos;
    public static int dia;
    public static int mes;
    public static int anio;
    public static int minimoPersonal;
    public static int minimoFamiliar;
    public static int eurosPorHijo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fechaNacimiento = findViewById(R.id.etFecha);
        nHijos = findViewById(R.id.etHijos);
        salarioBruto = findViewById(R.id.etSalario);
        botonFecha = findViewById(R.id.buttonFecha);
        botonCalcular = findViewById(R.id.buttonCalcular);

        botonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Calendario.class);
                    startActivity(intent);
            }
        });

        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularseguridadSocial();
                calcularMinPersonal();
                calcularBaseImponible();
                calcularDescendientes();
                calcularMinimoFamiliar();

            }
        });

    }

    public void calcularseguridadSocial(){
        salario= Double.parseDouble((String) salarioBruto.getText());
        baseIRPF= salario * 0.0635;
    }

    public void calcularBaseImponible(){
        baseImponible = salario - rendimientoTrabajo - baseIRPF;
    }

    public void calcularDescendientes(){
        numeroHijos = Integer.parseInt(String.valueOf(nHijos.getText()));

        if (numeroHijos==0){
            eurosPorHijo=0;
        }
        if (numeroHijos==1){
            eurosPorHijo=2400;
        }
        if (numeroHijos==2){
            eurosPorHijo=2700+2400;
        }
        if (numeroHijos==3){
            eurosPorHijo=4000+2400+2700;
        }
        if (numeroHijos>4){
            eurosPorHijo=4500+4000+2400+2700;
        }
    }


    public void calcularMinPersonal(){
        Calendar ahora = Calendar.getInstance();
        int diaHoy = ahora.get(Calendar.DAY_OF_MONTH);
        int mesHoy = ahora.get(Calendar.MONTH);
        int anioHoy = ahora.get(Calendar.YEAR);

        if (anioHoy-anio <65){
            minimoPersonal =5550;
        }
        if (anioHoy-anio>65 && anioHoy-anio<75){
            minimoPersonal =6468;
        }
        if (anioHoy-anio>=75){
            minimoPersonal =7590;
        }
        if (anioHoy-anio==65){
            if (mesHoy-mes>0){
                minimoPersonal =6468;
            }
            if (mesHoy-mes==0){
                if (diaHoy-dia>0){
                    minimoPersonal =6468;
                } else {
                    minimoPersonal =5550;
                }
            } else {
                minimoPersonal =5550;
            }
        }

    }

    public void calcularMinimoFamiliar(){
        minimoFamiliar = minimoPersonal+eurosPorHijo;
    }



}