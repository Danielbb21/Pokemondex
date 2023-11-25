package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.DAOs.PokemonDAO;

public class MainActivity extends AppCompatActivity {
    private Button BtNewGame, btLoadGame;
    private static final String SHARED_PREFERENCES = "shared_preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BtNewGame = findViewById(R.id.BtNewGame);
        btLoadGame = findViewById(R.id.btLoadGame);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES, 0);
        boolean loaded = preferences.getBoolean("loaded", false);
        if(!loaded){
            btLoadGame.setVisibility(View.INVISIBLE);
        }
        else{
            btLoadGame.setVisibility(View.VISIBLE);
        }
        BtNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokemonDAO pd = new PokemonDAO(getApplicationContext());
                pd.resetTable();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("loaded", true);
                Intent intent = new Intent(getApplicationContext(), initials_pokemons.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        btLoadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_pokemons.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}