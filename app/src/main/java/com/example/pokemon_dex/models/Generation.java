package com.example.pokemon_dex.models;

import java.util.ArrayList;

public class Generation {
    private String name;
    private ArrayList<PokemonSpecies> pokemon_species = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PokemonSpecies> getPokemon_species() {
        return pokemon_species;
    }
}

