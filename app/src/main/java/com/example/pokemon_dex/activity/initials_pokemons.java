package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Generation;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.models.PokemonSpecies;
import com.example.pokemon_dex.services.FetchApi;
import com.example.pokemon_dex.models.DAOs.PokemonDAO;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class initials_pokemons extends AppCompatActivity {

    ImageView[] poke = new ImageView[3];

    Generation generation;

    Pokemon[] pokemon = new Pokemon[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initials_pokemons);
        poke[0] = findViewById(R.id.poke_first);
        poke[1] = findViewById(R.id.poke_second);
        poke[2] = findViewById(R.id.poke_third);
        Random r = new Random();
        int generationId = r.nextInt(9) + 1;
        Call<Generation> call = FetchApi.getPokemonByGeneration(generationId);
        call.enqueue(new Callback<Generation>() {
            @Override
            public void onResponse(Call<Generation> call, Response<Generation> response) {
                generation = response.body();
                assert generation != null;
                ArrayList<PokemonSpecies> pokelist = generation.getPokemon_species();
                int errorSum = 0;
                if(generationId == 5 || generationId == 9) errorSum = 1;
                for (int i = 0; i < 3; i++){
                    pokemon[i] = new Pokemon();
                    pokemon[i].setName(pokelist.get(i+errorSum).getName());
                    System.out.println(pokemon[i].getName());

                    Call<Pokemon> callName = FetchApi.getPokemonByName(pokemon[i].getName());
                    int finalI = i;
                    callName.enqueue(new Callback<Pokemon>() {
                        @Override
                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                            pokemon[finalI] = response.body();
                            System.out.println("name " + pokemon[finalI].getName() + "\nid "+pokemon[finalI].getId());
                            pokemon[finalI].setImage_url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+pokemon[finalI].getId()+".png");
                            loadImages();
                        }

                        @Override
                        public void onFailure(Call<Pokemon> call, Throwable t) {

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<Generation> call, Throwable t) {
            }
        });
    }

    private void loadImages(){
        int count = 0;
        for ( ImageView ivPokemon : poke) {
            FetchApi.getPokemonImageById(pokemon[count].getId(), this, ivPokemon.getId());


            int finalCount = count;
            ivPokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PokemonDAO pd = new PokemonDAO(getApplicationContext());
                    pd.salvar(pokemon[finalCount]);

                    Intent intent = new Intent(getApplicationContext(), my_pokemons.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });
            count++;
        }
    }


}