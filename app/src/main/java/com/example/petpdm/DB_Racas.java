package com.example.petpdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB_Racas extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DB_Racas(Context context) {
        super(context, "RACAS_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
    MyDB.execSQL("create table racas( raca TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    MyDB.execSQL("drop table if exists racas");
    onCreate(MyDB);
    }

    public void addRacas(Pet pet){
        ContentValues contentValues = new ContentValues();
        contentValues.put("raca",pet.getRaca());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert("racas",null,contentValues);
    }
    public List<Pet> getracaslist(){
        String sql = "select * from racas";//selecina tudo na tabela racas
        sqLiteDatabase = this.getReadableDatabase();//lê o DB
        List<Pet> storeRacas = new ArrayList<>();//cria uma lista para armazenar a informação
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);//cria ponteiro
        if (cursor.moveToFirst()){//posiciona o ponteiro
            do {
                String raca = cursor.getString(0);
                storeRacas.add(new Pet(raca));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeRacas;//retorna lista que pegou no DB
    }
    public void updateRacas(Pet pet){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("raca",pet.getRaca());
        sqLiteDatabase.update("racas", contentValues,"raca = ?" ,new String[]{String.valueOf(pet.getRaca())});
    }
    public void deleteRacas(String raca){
        sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.delete("racas","raca = ?",new String[]{String.valueOf(raca)});
    }
}
