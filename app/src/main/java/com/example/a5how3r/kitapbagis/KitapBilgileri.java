package com.example.a5how3r.kitapbagis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class KitapBilgileri extends AppCompatActivity {

    TextView txtKitapAdi,txtKitapTuru,txtKitapDili,txtYazarAdi;
    int KitapId;
    int KoyanKullaniciId;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_bilgileri);

        KitapId = getIntent().getExtras().getInt("KitapId");

        String sql="select KitapAdi,KitapTuru,KitapDili,YazarAdi,KullaniciId from KITAPBILGISI where Id="+KitapId+"";


        database = new Database(getApplicationContext());

        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        cursor.moveToNext();

        txtKitapAdi = findViewById(R.id.txtKitapAdi);
        txtKitapTuru = findViewById(R.id.txtKitapTuru);
        txtKitapDili = findViewById(R.id.txtKitapDili);
        txtYazarAdi = findViewById(R.id.txtYazarAdi);

        txtKitapAdi.setText("Kitap Adı : "+cursor.getString(0));
        txtKitapTuru.setText("Kitap türü : "+cursor.getString(1));
        txtKitapDili.setText("Kitap Adı : "+cursor.getString(2));
        txtYazarAdi.setText("Yazar adı : "+cursor.getString(3));
        KoyanKullaniciId = cursor.getInt(4);

        Button btnOduncAl = findViewById(R.id.btnOduncAl);
        btnOduncAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(KitapBilgileri.this).create();
                alertDialog.setTitle("Ödünç Almak İstiyor Musunuz?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Evet",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String sql="update KITAPBILGISI set AlindiMi=1 where Id="+KitapId+"";
                                SQLiteDatabase sqLiteDatabase1 = database.getWritableDatabase();
                                sqLiteDatabase1.execSQL(sql);

                                Calendar calendar = Calendar.getInstance();
                                int yil = calendar.get(Calendar.YEAR);
                                int ay = calendar.get(Calendar.MONTH)+1;
                                int gun = calendar.get(Calendar.DAY_OF_MONTH);
                                String tarih = gun+"-"+ay+"-"+yil;

                                sql="insert into KITAPLARIM(KitapId,AldigimGun,KullaniciId) values("+KitapId+",'"+tarih+"',"+Kullanicilar.Id+")";

                                sqLiteDatabase1.execSQL(sql);

                                sql ="insert into MESAJLARIM(FromKullaniciId,ToKullaniciId,KitapId) values("+Kullanicilar.Id+","+KoyanKullaniciId+","+KitapId+")";
                                sqLiteDatabase1.execSQL(sql);

                                Toast.makeText(getApplicationContext(),"İşlem Tamam",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(),AnaEkran.class);
                                startActivity(i);

                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Hayır",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

    }
}
