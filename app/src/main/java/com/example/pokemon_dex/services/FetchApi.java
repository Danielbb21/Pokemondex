package com.example.pokemon_dex.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.pokemon_dex.models.Generation;
import com.example.pokemon_dex.models.Pokemon;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

public class FetchApi {

    private static Retrofit retrofit =  new Retrofit.Builder()
                                            .baseUrl("https://pokeapi.co/api/v2/")
                                             .addConverterFactory(GsonConverterFactory.create()).build();

    public static Call<Pokemon> getPokemonByID(int id){

        PokemonService pS = retrofit.create(PokemonService.class);
        Call<Pokemon> call = pS.getPokemonByID(id);
       return call;
    }
    public static Call<Pokemon> getPokemonByName(String name){

        PokemonService pS = retrofit.create(PokemonService.class);
        Call<Pokemon> call = pS.getPokemonByName(name);
        return call;
    }
    public static Bitmap getPokemonImageById(int pokemonId, Activity activity, int imageViewId) {
        AtomicReference<Bitmap> image = new AtomicReference<>();
        Thread t = new Thread(() -> {
            try {
                InputStream in = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ pokemonId +".png").openStream();
                image.set(BitmapFactory.decodeStream(in));

            } catch (Exception e) {
                e.printStackTrace();
            }
            activity.runOnUiThread(() -> {
                if (image != null) {
                    ImageView img = activity.findViewById(imageViewId);
                    img.setImageBitmap(image.get());
                }
            });
        });
        t.start();
        return image.get();
    }

    public static Call<Generation> getPokemonByGeneration(int id){

        PokemonService pS = retrofit.create(PokemonService.class);
        Call<Generation> call = pS.getPokemonByGeneration(id);
        return call;
    }

}
