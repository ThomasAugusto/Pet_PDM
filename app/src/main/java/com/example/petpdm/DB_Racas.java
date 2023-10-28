package com.example.petpdm;

import android.content.ContentValues;
import android.content.Context;
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
    public Boolean insertData(String raca) {
        SQLiteDatabase MyDB = this.getWritableDatabase();//acessa DB para escrever
        ContentValues contentValues = new ContentValues();
        contentValues.put("raca", raca);
        long result = MyDB.insert("racas", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<String> getracaslist(){
        String sql = "select * from racas";//selecina tudo na tabela racas
        sqLiteDatabase = this.getReadableDatabase();//lê o DB
        List<String> storeRacas = new ArrayList<>();
    }
}
