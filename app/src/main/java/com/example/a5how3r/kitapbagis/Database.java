package com.example.a5how3r.kitapbagis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DbName = "SABIT";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context)
    {
        super(context, DbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE KULLANICILAR(Id INTEGER PRIMARY KEY AUTOINCREMENT,KullaniciAdi TEXT,Sifre TEXT,Adi TEXT,Soyadi,Adres TEXT,DogumYili TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE KITAPBILGISI(Id INTEGER PRIMARY KEY AUTOINCREMENT,KitapAdi TEXT,KitapTuru TEXT,KitapDili TEXT,YazarAdi TEXT,AlindiMi INTEGER,KullaniciId INTEGER )");
        sqLiteDatabase.execSQL("CREATE TABLE KITAPLARIM(Id INTEGER PRIMARY KEY AUTOINCREMENT,KitapId INTEGER,AldigimGun TEXT,KullaniciId)");
        sqLiteDatabase.execSQL("CREATE TABLE MESAJLARIM(Id INTEGER PRIMARY KEY AUTOINCREMENT,FromKullaniciId INTEGER,ToKullaniciId INTEGER,KitapId INTEGER)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KULLANICILAR");

        onCreate(sqLiteDatabase);
    }
}

