package com.example.ejercicio4repasoexamen;

import android.content.Intent;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio4repasoexamen.databinding.ActivityMainBinding;
import com.example.ejercicio4repasoexamen.modelos.Inmueble;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Inmueble> inmuebles;

    private ActivityResultLauncher<Intent> crearInmueble;
    private ActivityResultLauncher<Intent> editarInmueble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        inmuebles = new ArrayList<>();

        inicializarLaunchers();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearInmueble.launch(new Intent(MainActivity.this, CrearInmuebleActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        crearInmueble = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                inmuebles.add(inmueble);
                                mostrarInmueblesContenedor();
                            }
                        }
                    }
                });

        editarInmueble = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null && result.getData().getExtras() != null){
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                int posicion = result.getData().getExtras().getInt("POSICION");
                                inmuebles.set(posicion,inmueble);
                                mostrarInmueblesContenedor();
                            }
                        }
                    }
                }
        );
    }

    private void mostrarInmueblesContenedor() {
        binding.contentMain.contenedor.removeAllViews();

        for (int i = 0; i < inmuebles.size(); i++) {
            Inmueble inmueble = inmuebles.get(i);

            View inmuebleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.inmueble_model_view,null);
            TextView lblCalle = inmuebleView.findViewById(R.id.lblCalleInmuebleView);
            TextView lblNumero = inmuebleView.findViewById(R.id.lblNumeroInmuebleView);
            TextView lblProvincia = inmuebleView.findViewById(R.id.lblProvinciaInmuebleView);
            RatingBar rbValoracion = inmuebleView.findViewById(R.id.rbValoracionInmuebleView);

            lblCalle.setText(inmueble.getCalle());
            lblNumero.setText(String.valueOf(inmueble.getNumero()));
            lblProvincia.setText(inmueble.getProvincia());
            rbValoracion.setRating(inmueble.getValoracion());

            int finalI = i;
            inmuebleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,EditarInmuebleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE",inmueble);
                    bundle.putInt("POSICION", finalI);
                    intent.putExtras(bundle);
                    editarInmueble.launch(intent);
                }
            });
            binding.contentMain.contenedor.addView(inmuebleView);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("INMUEBLES",inmuebles);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<Inmueble> temp = (ArrayList<Inmueble>) savedInstanceState.getSerializable("INMUEBLES");
        inmuebles.addAll(temp);
        mostrarInmueblesContenedor();
    }
}