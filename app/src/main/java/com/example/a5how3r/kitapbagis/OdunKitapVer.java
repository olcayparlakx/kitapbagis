package com.example.a5how3r.kitapbagis;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OdunKitapVer extends AppCompatActivity {

    Button btnOduncVer,btnKitapMagaza;

    EditText kitapAdi,kitapTuru,yazarAdi,kitapDili;
    Spinner sKitapTuru,sKitapDili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odun_kitap_ver);




        btnOduncVer = findViewById(R.id.btnOduncVer);

        btnOduncVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kitapAdi.getText().toString().length()==0 || yazarAdi.getText().toString().length()==0)
                {
                    return;
                }
                String kitapTuru = sKitapTuru.getSelectedItem().toString();
                String kitapDili = sKitapDili.getSelectedItem().toString();

                Database database = new Database(getApplicationContext());

                SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

                String sql="insert into KITAPBILGISI(KitapAdi,KitapDili,KitapTuru,YazarAdi,AlindiMi,KullaniciId) values('"+kitapAdi.getText().toString()+"','"+kitapDili+"','"+kitapTuru+"','"+yazarAdi.getText().toString()+"',0,"+Kullanicilar.Id+")";

                sqLiteDatabase.execSQL(sql);

                Toast.makeText(getApplicationContext(),"Kitap Eklendi",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),AnaEkran.class);
                startActivity(i);


            }
        });

        sKitapTuru = findViewById(R.id.spinnerKitapTuru);

        List<String> kitapTurleri = new ArrayList<>();

        String[] dkitap = {"Bilim","Biyografi","Deneme","Edebiyat","Eğitim","Felsefe","Masal","Mizah","Şiir","Tarih"};
        for(int i=0;i<dkitap.length;i++)
            kitapTurleri.add(dkitap[i]);


        ArrayAdapter<String> adapterKitapTuru = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,kitapTurleri);
        sKitapTuru.setAdapter(adapterKitapTuru);


        sKitapDili = findViewById(R.id.spinnerKitapDili);

        List<String> kitapDilleri = new ArrayList<>();

        String[] dkitapDili = {"Türkçe","İngilizce","Çince","Rusça"};
        for(int i=0;i<dkitapDili.length;i++)
            kitapDilleri.add(dkitapDili[i]);


        ArrayAdapter<String> adapterKitapDili = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,kitapDilleri);
        sKitapDili.setAdapter(adapterKitapDili);



        kitapAdi = findViewById(R.id.editKitapAdi);
        yazarAdi = findViewById(R.id.editYazarAdı);







    }
}
