package com.example.a5how3r.kitapbagis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Bilgilerim extends AppCompatActivity {

    TextView txtKullaniciAdi,txtAd,txtSoyad,txtAdres,txtDogumYili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgilerim);

        txtKullaniciAdi = findViewById(R.id.txtKullaniciAdi);
        txtAd = findViewById(R.id.txtAd);
        txtSoyad = findViewById(R.id.txtSoyad);
        txtAdres = findViewById(R.id.txtAdres);
        txtDogumYili = findViewById(R.id.txtDogumYili);

        txtKullaniciAdi.setText("Kullanıcı adı : "+Kullanicilar.KullaniciAdi);
        txtAd.setText("Ad : "+Kullanicilar.Adi);
        txtSoyad.setText("Soyad : "+Kullanicilar.Soyadi);
        txtAdres.setText("Adres : "+Kullanicilar.Adresi);
        txtDogumYili.setText("Dogum Yılı : "+Kullanicilar.DogumYili);
    }
}
