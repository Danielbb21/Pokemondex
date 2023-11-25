package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.FetchApi;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class catch_pokemon extends AppCompatActivity {

    private TextView txtPokemon;
    private Button btCatch;
    private Pokemon pokemon;

    private ImageView catchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon);
        txtPokemon = findViewById(R.id.txtPokemon);
        catchView = findViewById(R.id.CatchView);
        btCatch = findViewById(R.id.btCatch);
        btCatch.setVisibility(View.GONE);
        Random r = new Random();
        int pokeId = r.nextInt(1017) + 1;
        Call<Pokemon> call = FetchApi.getPokemonByID(pokeId);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                if(response.isSuccessful()){
                    pokemon = response.body();
                    txtPokemon.setText(pokemon.getName());
                    btCatch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                System.out.println(t);
            }
        });
        FetchApi.getImageByURL( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ pokeId +".png", this, R.id.CatchView);

        btCatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemonCaptured(pokemon.getCapture_rate())){
                    pokemon.setImage_url("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ pokeId +".png");
                    Intent intent = new Intent(getApplicationContext(), catched_pokemon.class);
                    intent.putExtra("name", pokemon.getName());
                    intent.putExtra("id", pokemon.getId());
                    intent.putExtra("capture_rate", pokemon.getCapture_rate());
                    intent.putExtra("image_url", pokemon.getImage_url());
                    startActivity(intent);
                }
                else{
                    if(checkRunAway(pokemon.getCapture_rate())){
                        catchView.setImageDrawable(getResources().getDrawable(R.drawable.fumaca, null));
                        btCatch.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(catch_pokemon.this, "Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean pokemonCaptured(int catch_rate){
        Random r = new Random();
        int catchRoll = r.nextInt(101);
        return catchRoll <= catch_rate;
    }

    private boolean checkRunAway(int catch_rate){
        Random r = new Random();
        int catchRoll = r.nextInt(101);
        return catchRoll >= catch_rate;
    }



}