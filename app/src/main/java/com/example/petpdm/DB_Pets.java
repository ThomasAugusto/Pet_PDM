package com.example.petpdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DB_Pets extends SQLiteOpenHelper {
    public DB_Pets(Context context) {
        super(context, "PET_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table pets(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, peso REAL, raca TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists pets");
        onCreate(MyDB);
    }
    public Boolean insertData(String nome,double peso,String raca) {
        SQLiteDatabase MyDB = this.getWritableDatabase();//acessa DB para escrever
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("peso",peso);
        contentValues.put("raca",raca);
        long result = MyDB.insert("pets", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<Pet> getPetList() {
        String sql = "select * from pets";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        List<Pet> storepets = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                Double peso = cursor.getDouble(1);
                String raca = cursor.getString(2);
                storepets.add(new Pet(nome,peso,raca));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storepets;
    }

}
