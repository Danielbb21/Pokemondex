package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemon_dex.R;

public class forest_pokemon extends AppCompatActivity {

    Button BtProcurar, btVerLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest_pokemon);

        BtProcurar = findViewById(R.id.BtProcurar);
        btVerLista = findViewById(R.id.btVerLista);
        BtProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), catch_pokemon.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        btVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_pokemons.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}