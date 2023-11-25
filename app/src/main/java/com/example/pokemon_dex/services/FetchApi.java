package com.example.pokemon_dex.services;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.pokemon_dex.models.Pokemon;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class FetchApi {

    private static Retrofit retrofit =  new Retrofit.Builder()
                                            .baseUrl("https://pokeapi.co/api/v2/")
                                             .addConverterFactory(GsonConverterFactory.create()).build();

    public static Call<Pokemon> getPokemonByID(int id){

        PokemonService pS = retrofit.create(PokemonService.class);
        Call<Pokemon> call = pS.getPokemonByID(id);
       return call;
    }
    public static Bitmap getImageByURL(String url, Activity activity, int imageViewId) {
        AtomicReference<Bitmap> image = new AtomicReference<>();
        Thread t = new Thread(() -> {
            try {
                InputStream in = new URL(url).openStream();
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

}
