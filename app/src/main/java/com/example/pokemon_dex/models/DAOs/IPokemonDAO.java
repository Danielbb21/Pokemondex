package com.example.pokemon_dex.models.DAOs;

import com.example.pokemon_dex.models.Pokemon;

import java.util.List;

public interface IPokemonDAO {

    public boolean salvar(Pokemon pokemon);

    public List<Pokemon> listar();

    public void resetTable();
}
