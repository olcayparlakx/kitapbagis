package com.example.a5how3r.kitapbagis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AnaEkran extends AppCompatActivity {
Button btnKitapMagaza,btnKitaplarim;
    Button btnOduncVer,btnMesajlarim;
    Button btnCikis;
    Button btnBilgilerim;
    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);


        btnBilgilerim = findViewById(R.id.btnBilgilerim);
        btnBilgilerim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Bilgilerim.class);
                startActivity(i);
            }
        });

        btnCikis = findViewById(R.id.btnCikis);
        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(AnaEkran.this).create();
                alertDialog.setTitle("Çıkış Yapmak İstiyor Musunuz Mi?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Evet",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {



                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
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

        btnKitaplarim = findViewById(R.id.btnKitaplarim);
        btnKitaplarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Kitaplarim.class);
                startActivity(i);
            }
        });


        btnOduncVer = findViewById(R.id.btnOduncVer2);

        btnOduncVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),OdunKitapVer.class);
                startActivity(i);
            }
        });

        btnKitapMagaza = findViewById(R.id.btnKitapMagaza2);
        btnKitapMagaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"qweqweqwe",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),KitapMagazasi.class);
                startActivity(i);
            }
        });

        btnMesajlarim = findViewById(R.id.btnIstekler);
        btnMesajlarim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Mesajlarim.class);
                startActivity(i);
            }
        });
    }
}
