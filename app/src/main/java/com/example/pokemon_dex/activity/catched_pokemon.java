package com.example.pokemon_dex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.FetchApi;
import com.example.pokemon_dex.models.DAOs.PokemonDAO;

public class catched_pokemon extends AppCompatActivity {
    private ImageView pokeImage;
    private TextView txtCatchedName;
    private Pokemon pokemon;
    private Button btVerLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catched_pokemon);
        Bundle bundle = getIntent().getExtras();
        txtCatchedName = findViewById(R.id.txtCatchedName);
        pokeImage = findViewById(R.id.pokemonCatched);
        btVerLista = findViewById(R.id.btCatched);
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
            Glide.with(getApplicationContext())
                    .load(pokemon.getImage_url())
                    .into(pokeImage);
        }
        PokemonDAO pd = new PokemonDAO(getApplicationContext());
        pd.salvar(pokemon);

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