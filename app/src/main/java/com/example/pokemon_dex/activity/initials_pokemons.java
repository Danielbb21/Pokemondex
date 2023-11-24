package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pokemon_dex.R;

public class initials_pokemons extends AppCompatActivity {

    ImageView[] poke = new ImageView[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initials_pokemons);
        poke[0] = findViewById(R.id.poke_first);
        poke[1] = findViewById(R.id.poke_second);
        poke[2] = findViewById(R.id.poke_third);

        for ( ImageView pokemon: poke) {
            pokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), my_pokemons.class);
                    startActivity(intent);
                }
            });
        }
    }


}