package com.example.ejercicio4repasoexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ejercicio4repasoexamen.databinding.ActivityCrearInmuebleBinding;
import com.example.ejercicio4repasoexamen.modelos.Inmueble;

public class CrearInmuebleActivity extends AppCompatActivity {

    private ActivityCrearInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearCrearInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble;

                if ((inmueble = crearInmueble()) != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtCalleCrearInmueble.getText().toString().isEmpty() ||
            binding.txtNumeroCrearInmueble.getText().toString().isEmpty() ||
            binding.txtProvinciaCrearInmueble.getText().toString().isEmpty()){
            return null;
        }else{
            return new Inmueble(binding.txtCalleCrearInmueble.getText().toString(),
                    Integer.parseInt(binding.txtNumeroCrearInmueble.getText().toString()),
                    binding.txtProvinciaCrearInmueble.getText().toString(),
                    binding.rbValoracionCrearInmueble.getRating());
        }
    }
}