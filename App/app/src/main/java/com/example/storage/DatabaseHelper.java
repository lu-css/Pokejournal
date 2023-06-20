package com.example.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_pokejournal";
    private static final String TABLE_FAVORITOS = "tbl_favoritos";
    private static final String COLUMN_FAVORITOS_ID = "favorito_id";
    private static final String COLUMN_FAVORITOS_POKEMON = "pokemon";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %S (" +
                "%s integer primary key autoincrement," +
                "%s text" +
                ")",
        TABLE_FAVORITOS, COLUMN_FAVORITOS_ID, COLUMN_FAVORITOS_POKEMON);

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
        onCreate(sqLiteDatabase);
    }

    public boolean insertPokemon(String pokemonId){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues content = new ContentValues();

        content.put(COLUMN_FAVORITOS_POKEMON, pokemonId);

        boolean success = db.insert(TABLE_FAVORITOS, null, content) > 0;

        return success;
     }

    public ArrayList<String> getAllFavPokemons(){
        ArrayList<String> pokemons = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("SELECT * FROM %s", TABLE_FAVORITOS);

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            int index = cursor.getColumnIndex(COLUMN_FAVORITOS_POKEMON);
            String pokemonId = cursor.getString(index);

            pokemons.add(pokemonId);
            cursor.moveToNext();
        }

        cursor.close();

        return pokemons;
    }
}
