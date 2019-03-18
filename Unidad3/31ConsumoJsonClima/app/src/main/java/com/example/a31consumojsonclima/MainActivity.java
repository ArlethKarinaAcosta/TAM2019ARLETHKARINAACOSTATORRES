package com.example.a31consumojsonclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AsyncResponse  {

    ConexionWeb conexionWeb;
    TextView clima1,clima2,climat, nombreCiudad, elegirCiudad;
    Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clima1=findViewById(R.id.Clima1);
        clima2=findViewById(R.id.Clima2);
        climat=findViewById(R.id.climat);
        nombreCiudad = findViewById(R.id.ciudad);
        consultar = findViewById(R.id.button);
        elegirCiudad = findViewById(R.id.escogerCiudad);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarClima();
            }
        });
    }


    public void CargarClima() {
        try {
            conexionWeb = new ConexionWeb(MainActivity.this);
            String ciudadFinal = elegirCiudad.getText().toString();
            URL direcciopn = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ciudadFinal+",mx&APPID=a552162bc6ebbcf61400f7ef0a2d3394");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void procesarRespuesta(String r) {
        try {

            JSONObject object = new JSONObject(r);

            //JSONArray clima =           object.getJSONArray("weather");
            //StringBuilder sb = new StringBuilder();
            /*for (int i = 0; i < clima.length(); i++) {
                JSONObject objeto = clima.getJSONObject(i);
                String main = objeto.getString("main");
                String des = objeto.getString("description");
                sb.append(main+" : "+des+"         ");
            }*/
            nombreCiudad.setText("Ciudad: " + object.getString("name"));

            JSONObject coord = object.getJSONObject("coord");
            clima1.setText("Longitud: "+coord.getString("lon")+"\nLatitud: "+coord.getString("lat"));

            JSONObject  temperaturas = object.getJSONObject("main");
            double doubleTemperatura = Double.parseDouble(temperaturas.getString("temp")) - 273.15;
            double doubleTemperaturaMinima = Double.parseDouble(temperaturas.getString("temp_min")) - 273.15;
            double doubleTemperaturaMaxima = Double.parseDouble(temperaturas.getString("temp_max")) - 273.15;

            final DecimalFormat formatoDec = new DecimalFormat("#.#");
            formatoDec.setRoundingMode(RoundingMode.FLOOR);

            Double temperatura = new Double(formatoDec.format(doubleTemperatura));
            Double temperaturaMinima = new Double(formatoDec.format(doubleTemperaturaMinima));
            Double temperaturaMaxima = new Double(formatoDec.format(doubleTemperaturaMaxima));

            clima2.setText("\n"+"Temperatura: "+temperatura+"\nHumedad:"+temperaturas.getString("humidity")+"\nTemperatura minima: "+temperaturaMinima+"\nTemperatura maxima: "+temperaturaMaxima);


        }catch (JSONException e){
            System.out.println(""+e);
        }

    }
}
