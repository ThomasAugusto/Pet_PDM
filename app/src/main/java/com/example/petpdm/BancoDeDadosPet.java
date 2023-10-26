package com.example.petpdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoDeDadosPet extends SQLiteOpenHelper {
    public BancoDeDadosPet(Context context) {
        super(context, "PET_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE pet(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "peso REAL NOT NULL," +
                "raca TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO pet(nome,peso,raca)" +
                "VALUES('pet1',5,'raca a')";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
