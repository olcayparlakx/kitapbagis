package com.example.a5how3r.kitapbagis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        return;
    }


    Button btnKayitOl,btnGiris;
    EditText kullaniciAdi,sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kullaniciAdi = findViewById(R.id.editKullaniciAdi);
        sifre = findViewById(R.id.editSifre);

        btnGiris =(Button) findViewById(R.id.btnGiris2);
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kullaniciAdi.getText().toString().length()==0 || sifre.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Kullanıcı Adı veya Şifre Boş Girilemez", Toast.LENGTH_SHORT).show();
                    return;
                }

                Database database = new Database(getApplicationContext());

                SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

                String sql = "select Id,Adi,Soyadi,Adres,DogumYili from KULLANICILAR where KullaniciAdi='"+kullaniciAdi.getText().toString()+"' AND Sifre='"+sifre.getText().toString()+"'";
                Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

                if(cursor.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(), "Kullanıcı Adı veya Şifre Yanlış", Toast.LENGTH_SHORT).show();
                    return;
                }
                cursor.moveToNext();
                Kullanicilar.Id=cursor.getInt(0);
                Kullanicilar.Adi=cursor.getString(1);
                Kullanicilar.Soyadi=cursor.getString(2);
                Kullanicilar.Adresi=cursor.getString(3);
                Kullanicilar.DogumYili=cursor.getString(4);
                Kullanicilar.KullaniciAdi=kullaniciAdi.getText().toString();

                Toast.makeText(getApplicationContext(), "Giriş Yapıldı", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),AnaEkran.class);
                startActivity(i);
            }
        });

        btnKayitOl = findViewById(R.id.btnKayitOl);
        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KayitOl.class);
                startActivity(i);
            }
        });
    }
}
