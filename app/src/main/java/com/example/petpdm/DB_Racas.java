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


    public DB_Racas(Context context) {
        super(context, "DB_RACAS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table racas(raca TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists racas");//cancela a criação se a tabela já existe
        onCreate(MyDB);
    }
    public Boolean insertData(String racas) {
        SQLiteDatabase MyDB = this.getWritableDatabase();//acessa DB para escrever
        ContentValues contentValues = new ContentValues();
        contentValues.put("raca", racas);
        long result = MyDB.insert("racas", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkracas(String racas){//procura no DB usuário se ele existe e retorna verdadeiro caso exista
        SQLiteDatabase MyDB = this.getReadableDatabase();//permissão somente para ler
        Cursor cursor = MyDB.rawQuery("select * from racas where raca = ?",new String[]{racas});
        if (cursor.getCount()>0){//retorna verdadeiro quando o cursor encontra o item
            return true;
        }else {
            return false;
        }
    }
}
