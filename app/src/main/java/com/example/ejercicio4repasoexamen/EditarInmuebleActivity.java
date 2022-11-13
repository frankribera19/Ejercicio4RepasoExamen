package com.example.ejercicio4repasoexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ejercicio4repasoexamen.databinding.ActivityEditarInmuebleBinding;
import com.example.ejercicio4repasoexamen.modelos.Inmueble;

public class EditarInmuebleActivity extends AppCompatActivity {

    private ActivityEditarInmuebleBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Inmueble inmueble = (Inmueble) bundle.getSerializable("INMUEBLE");

        binding.txtCalleEditInumueble.setText(inmueble.getCalle());
        binding.txtNumeroEditInmueble.setText(String.valueOf(inmueble.getNumero()));
        binding.txtProvinciaEditInmueble.setText(inmueble.getProvincia());
        binding.rbValoracionEditInmueble.setRating(inmueble.getValoracion());

        binding.btnCrearEditInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inmueble inmueble1;

                if ((inmueble1 = crearInmueble()) != null){
                    Intent intent1 = new Intent();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("INMUEBLE", inmueble1);
                    int pos = bundle.getInt("POSICION");
                    bundle1.putInt("POSICION",pos);
                    intent1.putExtras(bundle1);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtCalleEditInumueble.getText().toString().isEmpty() ||
                binding.txtNumeroEditInmueble.getText().toString().isEmpty() ||
                binding.txtProvinciaEditInmueble.getText().toString().isEmpty() )
            return null;
        return new Inmueble(
                binding.txtCalleEditInumueble.getText().toString(),
                Integer.parseInt(binding.txtNumeroEditInmueble.getText().toString()),
                binding.txtProvinciaEditInmueble.getText().toString(),
                binding.rbValoracionEditInmueble.getRating()
        );
    }
}