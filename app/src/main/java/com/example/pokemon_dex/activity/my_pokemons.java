package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.models.DAOs.PokemonDAO;

import java.util.ArrayList;

public class my_pokemons extends AppCompatActivity {

    Button btCacar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pokemons);
        PokemonDAO pd = new PokemonDAO(getApplicationContext());
        ArrayList<Pokemon> pokemons = pd.listar();
        System.out.println(pokemons.get(0).getName());
        btCacar = findViewById(R.id.btCacar);
        btCacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), forest_pokemon.class);
                 startActivity(intent);
                 finishAffinity();
            }
        });
    }
}