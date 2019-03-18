package mx.edu.ittepic.unidad3_31jsonclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AsyncResponse  {

    ConexionWeb conexionWeb;
    TextView serie1,serie2, nombreSerie;
    EditText elegirSerie;
    Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serie1=findViewById(R.id.series1);
        serie2=findViewById(R.id.series2);
        nombreSerie = findViewById(R.id.serie);
        consultar = findViewById(R.id.button);
        elegirSerie = findViewById(R.id.escogerSerie);
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
            String serieFinal = elegirSerie.getText().toString();
            String cad = "http://www.omdbapi.com/?t="+serieFinal+"&apikey=f8f6866f";
            URL direcciopn = new URL(cad);
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
/*
            JSONObject coord = object.getJSONObject("coord");
            climat.setText("Clima de "+object.getString("name"));
            clima1.setText("Nombre : "+object.getString("name")+"\nLongitud: "+coord.getString("lon")+"\nLatitud: "+coord.getString("lat"));

            JSONObject  temperaturas = object.getJSONObject("main");
            double doubleTemperatura = Double.parseDouble(temperaturas.getString("temp")) - 273.15;
            double doubleTemperaturaMinima = Double.parseDouble(temperaturas.getString("temp_min")) - 273.15;
            double doubleTemperaturaMaxima = Double.parseDouble(temperaturas.getString("temp_max")) - 273.15;

            final DecimalFormat formatoDec = new DecimalFormat("#.#");
            formatoDec.setRoundingMode(RoundingMode.FLOOR);

            Double temperatura = new Double(formatoDec.format(doubleTemperatura));
            Double temperaturaMinima = new Double(formatoDec.format(doubleTemperaturaMinima));
            Double temperaturaMaxima = new Double(formatoDec.format(doubleTemperaturaMaxima));

            clima2.setText("\n"+"Temperatura: "+temperatura+"\nHumedad:"+temperaturas.getString("pressure")+"\nTemperatura minima: "+temperaturaMinima+"\nTemperatura maxima: "+temperaturaMaxima);
*/
            nombreSerie.setText("Serie: " + object.getString("Title"));

            serie1.setText("Año: "+object.getString("Year")+"\nLanzamiento: "+object.getString("Released")+"\nGenero: "+object.get("Genre"));

            serie2.setText("\n"+"Actores: "+object.getString("Actors")+"\nEpisodio piloto:"+object.getString("Plot")+"\nLenguaje: "+object.getString("Language")+"\nPaís: "+object.getString("Country"));


        }catch (JSONException e){
            System.out.println(""+e);
        }


    }
}
