package com.example.a42_lab_asyntask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNumero;
    TextView txtResultado;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero = findViewById(R.id.edtNumero);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumero.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "No se ha ingresado ni un numero", Toast.LENGTH_SHORT).show();
                }
                else{
                    AsyncTarea asyncTarea = new AsyncTarea();

                    int numero = Integer.parseInt(edtNumero.getText().toString());

                    if(numero==0){
                        txtResultado.setText("");
                    }
                    if(numero==1){
                        txtResultado.setText("0");
                    }
                    if(numero>=2){
                        numero = numero-2;
                        asyncTarea.execute(numero);
                    }
                }
            }
        });
    }

    private class  AsyncTarea extends AsyncTask<Integer, Integer,Boolean> {
        String res="0,1,";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            int n1 = 0;
            int n2 = 1;
            int aux;
            int limite = params[0];
            int i=1;
            while ((i) <= limite) {

                aux = n1;
                n1 = n2;
                n2 = aux + n1;
                i++;
                res += n2+",";
            }

            return true;
        }
        private void UnSegundo() {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            txtResultado.setText(res);
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"No finaliza",Toast.LENGTH_SHORT).show();

        }


    }
}
