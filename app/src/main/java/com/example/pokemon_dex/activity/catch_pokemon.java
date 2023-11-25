package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.services.FetchApi;

import java.util.Random;

public class catch_pokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_pokemon);
        Random r = new Random();
        int pokeId = r.nextInt(1018);

        FetchApi.getImageByURL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ pokeId +".png", this, R.id.CatchView);
    }
}