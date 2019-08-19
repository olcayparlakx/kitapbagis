package com.example.a5how3r.kitapbagis;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOl extends AppCompatActivity {

    EditText adi,soyadi,kullaniciAdi,sifre,adres,dogumYili;
    Button btnKayitOl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);


        adi = findViewById(R.id.editAdi);
        soyadi = findViewById(R.id.editSoyadi);
        kullaniciAdi = findViewById(R.id.editKullaniciAdi);
        sifre = findViewById(R.id.editSifre);
        adres = findViewById(R.id.editAdres);
        dogumYili = findViewById(R.id.editDogumYiliniz);

        btnKayitOl = findViewById(R.id.btnKayitOl);

        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adi.getText().toString().length()==0 || soyadi.getText().toString().length()==0 ||
                        kullaniciAdi.getText().toString().length()==0 ||  sifre.getText().toString().length()==0 ||
                        adres.getText().toString().length()==0 || dogumYili.getText().toString().length()==0) {
                    Toast.makeText(getApplicationContext(), "Bilgileri Doldurunuz", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                Database database = new Database(getApplicationContext());

                SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

                String sql="insert into KULLANICILAR(KullaniciAdi,Sifre,Adi,Soyadi,Adres,DogumYili) values('"+kullaniciAdi.getText().toString()+"','"+sifre.getText().toString()+"','"+adi.getText().toString()+"'," +
                        "'"+soyadi.getText().toString()+"','"+adres.getText().toString()+"','"+dogumYili.getText().toString()+"')";


                    sqLiteDatabase.execSQL(sql);

                    Toast.makeText(getApplicationContext(), "Kayıt Olundu", Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
