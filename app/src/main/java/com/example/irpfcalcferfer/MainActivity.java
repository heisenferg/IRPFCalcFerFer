package com.example.irpfcalcferfer;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView fechaNacimiento;
    TextView nHijos;
    TextView salarioBruto;
    Button botonFecha;
    Button botonCalcular;

    public static Double baseIRPF;
    public static final int rendimientoTrabajo=2000;
    public static int numeroHijos;
    public static int dia;
    public static int mes;
    public static int anio;

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
                calcularBaseImponible();
            }
        });

    }

    public void calcularBaseImponible(){
        Double salario= Double.parseDouble((String) salarioBruto.getText());
        baseIRPF= salario * 0.0635;
    }

    public void calcularMinFamiliar(){
        numeroHijos = Integer.parseInt(String.valueOf(nHijos.getText()));

        if (numeroHijos==0){

        }
    }


    public void calcularEdad(){
        Calendar ahora = Calendar.getInstance();
        int diaHoy = ahora.get(Calendar.DAY_OF_MONTH);
        int mesHoy = ahora.get(Calendar.MONTH);
        int anioHoy = ahora.get(Calendar.YEAR);



    }



}