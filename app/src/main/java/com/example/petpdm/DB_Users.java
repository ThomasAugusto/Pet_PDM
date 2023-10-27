package com.example.petpdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Users extends SQLiteOpenHelper {
    public static final String DBNAME = "USERS_DB";

    public DB_Users(Context context){
        super(context,"USERS_DB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       MyDB.execSQL("create table users(username TEXT primary key,password TEXT)");//cria a tabela
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");//cancela a criação se a tabela já existe
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();//acessa DB para escrever
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkusername(String username){//procura no DB usuário se ele existe e retorna verdadeiro caso exista
        SQLiteDatabase MyDB = this.getReadableDatabase();//permissão somente para ler
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?",new String[]{username});
        if (cursor.getCount()>0){//retorna verdadeiro quando o cursor encontra o item
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkpassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}