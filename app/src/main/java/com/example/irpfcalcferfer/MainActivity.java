package com.example.irpfcalcferfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView fechaNacimiento;
    TextView nHijos;
    TextView salarioBruto;
    Button botonFecha;
    Button botonCalcular;

    String baseIRPF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fechaNacimiento = findViewById(R.id.etFecha);
        nHijos = findViewById(R.id.etHijos);
        salarioBruto = findViewById(R.id.etSalario);
        botonFecha = findViewById(R.id.buttonFecha);
        botonCalcular = findViewById(R.id.buttonCalcular);

        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularBaseImponible();
            }
        });

    }

    public void calcularBaseImponible(){
        Double baseImponible;
        Double salario= Double.parseDouble((String) salarioBruto.getText());
        baseImponible= salario * 0.0635;
        baseIRPF = String.valueOf(baseImponible);
    }


}