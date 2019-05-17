package com.example.a54laboratoriogiroscopio;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor mySensor;
    private SensorManager senMan;
    TextView txt_X,txt_Y,txt_Z;

    private XYPlot myXYPlot;
    ArrayList<Double> vector, vector2, vector3;
    public double datoX, datoX2, datoY, datoY2, datoZ, datoZ2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        senMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        //mySensor= senMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mySensor= senMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        txt_X=(TextView)findViewById(R.id.txtX);
        txt_Y=(TextView)findViewById(R.id.txtY);
        txt_Z=(TextView)findViewById(R.id.txtZ);
        myXYPlot =(XYPlot) findViewById(R.id.myXYPlot);

        vector = new ArrayList<Double>();
        vector2 = new ArrayList<Double>();
        vector3 = new ArrayList<Double>();

        setTitle("5.4 Laboratorio 1 - Giroscopio");

        myXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 0.5); //Incremento de 0.5
        myXYPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1); // Llega la grafica hasta el 1
        myXYPlot.getGraphWidget().getGridBackgroundPaint().setColor(Color.rgb(255, 255, 255)); //Color del fondo de la grafica (rojo)
        myXYPlot.getGraphWidget().getDomainGridLinePaint().setColor(Color.rgb(255, 0, 0)); //Color de la linea
        myXYPlot.getGraphWidget().getRangeGridLinePaint().setColor(Color.rgb(255, 0, 0)); //Color del rango
        myXYPlot.setRangeBoundaries(-0.5, 2, BoundaryMode.FIXED); //Altura de la grafica
        myXYPlot.setTitle("5.4 Laboratorio 1 - Giroscopio");

        for (int i=0; i<20; i++){
            datoX = datoX + 0.5;
            vector.add(datoX);
            datoX2 = Math.random()*0.06;
            vector.add(datoX2);
            datoY = datoY + 0.5;
            vector2.add(datoY);
            datoY2 = Math.random()*0.02;
            vector2.add(datoY2);
            datoZ = datoZ + 0.5;
            vector3.add(datoZ);
            datoZ2 = Math.random()*0.04;
            vector3.add(datoZ2);
        }


        XYSeries series = new SimpleXYSeries(vector, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "X");
        LineAndPointFormatter seriesFormat = new LineAndPointFormatter(Color.rgb(127, 255, 0), 0x000000, 0x000000, null);
        myXYPlot.clear();
        myXYPlot.addSeries(series, seriesFormat);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                refrescar();
            }
        }, 0, 800); //Segundos que va a mandar a llamar el metodo de refrescar


        senMan.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txt_X.setText(""+event.values[0]);
        txt_Y.setText(""+event.values[1]);
        txt_Z.setText(""+event.values[2]);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void refrescar(){
        this.runOnUiThread(Graf);
    }

    private Runnable Graf = new Runnable() {
        @Override
        public void run() {

            datoX = datoX + 0.5;
            vector.remove(0);
            vector.add(datoX);
            datoX2 = Math.random()*0.06;
            vector.remove(0);
            vector.add(datoX2);
            datoY = datoY + 0.5;
            vector2.remove(0);
            vector2.add(datoY);
            datoY2 = Math.random()*0.02;
            vector2.remove(0);
            vector2.add(datoY2);
            datoZ = datoZ + 0.5;
            vector3.remove(0);
            vector3.add(datoZ);
            datoZ2 = Math.random()*0.04;
            vector3.remove(0);
            vector3.add(datoZ2);

            XYSeries series = new SimpleXYSeries(vector, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "X");
            LineAndPointFormatter seriesFormat = new LineAndPointFormatter(Color.rgb(127, 255, 0), 0x000000, 0x000000, null);
            myXYPlot.clear();
            myXYPlot.addSeries(series, seriesFormat);

            XYSeries series2 = new SimpleXYSeries(vector2, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "Y");
            LineAndPointFormatter seriesFormat2 = new LineAndPointFormatter(Color.rgb(255, 127, 0), 0x000000, 0x000000, null);
            myXYPlot.addSeries(series2, seriesFormat2);

            XYSeries series3 = new SimpleXYSeries(vector3, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED, "Z");
            LineAndPointFormatter seriesFormat3 = new LineAndPointFormatter(Color.rgb(0, 125, 255), 0x000000, 0x000000, null);
            myXYPlot.addSeries(series3, seriesFormat3);

            myXYPlot.redraw();
        }
    };
}
