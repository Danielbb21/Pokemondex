package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.adapters.PokemonAdapter;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.models.DAOs.PokemonDAO;

import java.util.ArrayList;
import java.util.List;

public class my_pokemons extends AppCompatActivity {

    Button btCacar;

    ArrayList<Pokemon> pokemons;
    RecyclerView listaPokemons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pokemons);
        PokemonDAO pd = new PokemonDAO(getApplicationContext());
        pokemons = pd.listar();
        System.out.println(pokemons.get(0).getName());
        btCacar = findViewById(R.id.btCacar);
        listaPokemons = findViewById(R.id.recyclerView);


        btCacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), forest_pokemon.class);
                 startActivity(intent);
                 finishAffinity();
            }
        });
        loadPokemons(pokemons);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void loadPokemons(ArrayList<Pokemon> lista) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
            listaPokemons.setLayoutManager(manager);
            listaPokemons.addItemDecoration(new DividerItemDecoration(
                    getApplicationContext(),
                    LinearLayout.VERTICAL)
            );
            PokemonAdapter adapter = new PokemonAdapter(lista, getApplicationContext());
            listaPokemons.setAdapter(adapter);
    }
}