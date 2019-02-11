package com.example.a13juegopulsor_arlethkarinaacostatorres;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView numero1, numero2, mensajeStatus;
    Button detener, again;
    CountDownTimer timer;
    double incremento, contador, ran1, ram2, n, numero_aleatorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero1 = findViewById(R.id.numero_aleatorio);
        numero2 = findViewById(R.id.numero_aleatorio2);
        mensajeStatus = findViewById(R.id.mensaje);
        detener = findViewById(R.id.parar);
        again = findViewById(R.id.otravez);

        incremento = 0.0;
        ran1 = 1.0;
        ram2 = 3.0;

        numero_aleatorio = (Math.random()*(ram2-ran1)+ran1);

        final DecimalFormat formatoDecimal = new DecimalFormat("#.#");
        formatoDecimal.setRoundingMode(RoundingMode.FLOOR); //Se hace uso de la clase BigDecimal por medio del método de RoundingMode para indicar el modo de redondeo y cuantos decimales se quieren.
        //Para un valor positivo.

        n=new Double(formatoDecimal.format(numero_aleatorio));
        numero1.setText(""+n);

        timer = new CountDownTimer(200000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador = new Double(formatoDecimal.format(incremento));
                numero2.setText(""+contador);
                incremento = incremento + 0.1;

                if(contador==3.0)
                {
                    mensajeStatus.setText("¡PERDISTE! :(");
                    timer.cancel();
                }
            }

            @Override
            public void onFinish() {
            numero2.setText(""+contador);
            }
        };

        timer.start();

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n==contador)
                {
                    mensajeStatus.setText("¡ERES UN GANADOR! :D");
                }
                if(contador<n || contador>n)
                {
                    mensajeStatus.setText("¡PERDISTE! :(");
                }
                timer.cancel();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana = new Intent(MainActivity.this, MainActivity.class);
                startActivity(ventana);
            }
        });
    }

}
