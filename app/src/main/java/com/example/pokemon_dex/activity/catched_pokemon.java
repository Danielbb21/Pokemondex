package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.FetchApi;

public class catched_pokemon extends AppCompatActivity {

    private TextView txtCatchedName;
    private Pokemon pokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catched_pokemon);
        Bundle bundle = getIntent().getExtras();
        txtCatchedName = findViewById(R.id.txtCatchedName);
        pokemon = new Pokemon();
        if(bundle.containsKey("name")){
            pokemon.setName(bundle.getString("name"));
            txtCatchedName.setText(pokemon.getName());
        }
        if(bundle.containsKey("id")){
            pokemon.setId(bundle.getInt("id"));
        }
        if(bundle.containsKey("capture_rate")){
            pokemon.setCapture_rate(bundle.getInt("capture_rate"));
        }
        if(bundle.containsKey("image_url")){
            pokemon.setImage_url(bundle.getString("image_url"));
            FetchApi.getImageByURL(pokemon.getImage_url(), this, R.id.pokemonCatched);
        }

    }
}