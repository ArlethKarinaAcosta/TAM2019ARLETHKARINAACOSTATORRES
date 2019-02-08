package com.ejemplo.rcview2;

//Es generar los componentes que se vayan a cargar en el Recycler así lo que se vayan a cargar en
//recycler así lo ponga aquí estará duplicado muchas veces.


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 20/02/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private String[] nombres;
    private String[] noControl;
    private String[] carrera;
    private Context pantalla;
    //private int[] foto;

    public RecyclerAdapter(String[] nombres, String[] noControl, String[] carrera, Context context) {
        this.nombres = nombres;
        this.noControl = noControl;
        this.carrera = carrera;
        this.pantalla = context;
        //this.foto = foto;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;


        //Enlazamos el adaptador con el archivo item_layout.
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.campo_nombre.setText(nombres[position]);
        holder.campo_numero.setText(noControl[position]);
        holder.campo_carreras.setText(carrera[position]);
        //holder.campo_foto.setImageResource(foto[position]);

        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pantalla, "Vas a actualizar: " + " Nombre: " +  nombres[position] + " Número de control: " + noControl[position] + " Carrera: " + carrera[position], Toast.LENGTH_LONG).show();
            }
        });

        holder.foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(pantalla, "Vas a eliminar: " + " Nombre: " +  nombres[position] + " Número de control: " + noControl[position] + " Carrera: " + carrera[position], Toast.LENGTH_LONG).show();

            }
        });


        //SE encarga de establecer la comunicacion entre el adaptador y esta clase, Se le envia la informacion quiero que se muestre y recibe la variable posicion.

    }

    @Override
    public int getItemCount() {
        return nombres.length;

        //Aquí se retorna el tamaño de la lista.

    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView campo_nombre;
        TextView campo_numero;
        TextView campo_carreras;
        ImageButton foto;
        ImageButton foto2;
        public RecyclerViewHolder(View itemView) {
            super(itemView);

            campo_nombre = itemView.findViewById(R.id.alumno_id);
            campo_numero = itemView.findViewById(R.id.no_control);
            campo_carreras = itemView.findViewById(R.id.carrera);
            foto = itemView.findViewById(R.id.imagen);
            foto2 = itemView.findViewById(R.id.deleteid);



            //En el constructor referenciamos los elementos del xml.



        }
    }

}
