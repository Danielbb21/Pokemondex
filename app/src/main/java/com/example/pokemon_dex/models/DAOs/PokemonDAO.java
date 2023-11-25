package com.example.pokemon_dex.models.DAOs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pokemon_dex.models.Pokemon;
import com.example.pokemon_dex.services.Helper.SQLiteDatabaseHelper;

import java.util.ArrayList;

public class PokemonDAO implements IPokemonDAO {

        private SQLiteDatabase objWrite;
        private SQLiteDatabase objRead;

        public PokemonDAO(Context ctx) {
            SQLiteDatabaseHelper db = new SQLiteDatabaseHelper(ctx);
            objWrite = db.getWritableDatabase();
            objRead = db.getReadableDatabase();
        }

        @Override
        public boolean salvar(Pokemon pokemon) {
            try {
                ContentValues cv = new ContentValues();
                cv.put("pokemon_id", pokemon.getId());
                cv.put("name", pokemon.getName());
                cv.put("image_url", pokemon.getImage_url());

                objWrite.insert("Pokemons", null, cv);

                Log.i("INFO BD", "Dados Salvos com Sucesso");
            } catch (Exception ex) {
                Log.i("INFO BD", "Falha na gravação dos dados");
                return false;
            }
            return true;
        }
        @Override
        public ArrayList<Pokemon> listar() {

            ArrayList<Pokemon> lista_pokemons = new ArrayList<>();

            try {
                String sql = "SELECT * FROM Pokemons";
                Cursor cursor = objRead.rawQuery(sql, null);

                int iid = cursor.getColumnIndexOrThrow("id");
                int iPokemon_id = cursor.getColumnIndexOrThrow("pokemon_id");
                int iName = cursor.getColumnIndexOrThrow("name");
                int iImage_url = cursor.getColumnIndexOrThrow("image_url");


                cursor.moveToFirst();

                do {
                    if (cursor.getCount() ==0) {break;}
                    Pokemon pokemon = new Pokemon();
                    pokemon.setTable_id(Integer.parseInt(cursor.getString(iid)));
                    pokemon.setId(Integer.parseInt(cursor.getString(iPokemon_id)));
                    pokemon.setName(cursor.getString(iName));
                    pokemon.setImage_url(cursor.getString(iImage_url));
                    lista_pokemons.add(pokemon);

                } while (cursor.moveToNext());

                Log.i("INFO BD", "Sucesso na listagem dos dados");
            } catch (Exception ex) {
                Log.i("INFO BD", "Falha na listagem dos dados: " + ex.getMessage());
                return null;
            }
            return lista_pokemons;
        }

    @Override
    public void resetTable() {
        objWrite.execSQL("DELETE FROM Pokemons");
    }
}

