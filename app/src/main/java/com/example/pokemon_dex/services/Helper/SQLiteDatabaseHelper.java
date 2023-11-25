package com.example.pokemon_dex.services.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "MYPOKEMONS";
    private static final int version = 1;

    public SQLiteDatabaseHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS Pokemons(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, pokemon_id INTEGER, name VARCHAR(255), image_url VARCHAR(255)) ");
            Log.i("INFO: ", "tabela pokemons criada com sucesso");
        }catch (Exception e){
            Log.i("INFO: ", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
