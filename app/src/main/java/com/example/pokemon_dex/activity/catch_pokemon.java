package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.FetchApi;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class catch_pokemon extends AppCompatActivity {

    private TextView txtPokemon;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon);
        txtPokemon = findViewById(R.id.txtPokemon);
        Random r = new Random();
        int pokeId = r.nextInt(1018);
        Call<Pokemon> call = FetchApi.getPokemonByID(pokeId);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                if(response.isSuccessful()){
                    pokemon = response.body();
                    System.out.println(pokemon.getName());
                    txtPokemon.setText(pokemon.getName());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                System.out.println(t);
            }
        });

        FetchApi.getImageByURL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ pokeId +".png", this, R.id.CatchView);
    }
}