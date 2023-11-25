package com.example.pokemon_dex.services;

import com.example.pokemon_dex.models.Generation;
import com.example.pokemon_dex.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {

    @GET("pokemon-species/{id}")
    Call<Pokemon> getPokemonByID(@Path("id") int id);

    @GET("pokemon-species/{name}")
    Call<Pokemon> getPokemonByName(@Path("name") String name);
    @GET("generation/{id}")
    Call<Generation> getPokemonByGeneration(@Path("id") int id);
}
