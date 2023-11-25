package com.example.pokemon_dex.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemon_dex.R;
import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.FetchApi;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.MyViewHolder> {
    private ArrayList<Pokemon> mypokemons;

    private Context myContext;
    public PokemonAdapter(ArrayList<Pokemon> pokemons, Context context) {
        mypokemons = pokemons;
        myContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pokemon_recycler, parent, false);
        return new MyViewHolder(ItemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pokemon pokemon = mypokemons.get(position);
        holder.pokenome.setText(pokemon.getName());
        URI uri = URI.create(pokemon.getImage_url());
        Glide.with(myContext)
                .load(pokemon.getImage_url())
                .into(holder.pokeminho);
    }

    @Override
    public int getItemCount() {
        return this.mypokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView pokenome;
        private ImageView pokeminho;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pokenome = itemView.findViewById(R.id.txtNamePokemon);
            pokeminho = itemView.findViewById(R.id.pokeminho);
        }
    }
}

