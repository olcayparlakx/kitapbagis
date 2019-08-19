package com.example.a5how3r.kitapbagis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Mesajlarim extends AppCompatActivity {

    ListView mesajlarim;
    List<Integer> listMesajId;
    List<String> listAdres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlarim);

        mesajlarim = findViewById(R.id.mesajlarim);

        final Database database = new Database(getApplicationContext());

        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

        String sql="select KITAPBILGISI.KitapAdi,KULLANICILAR.Adi,KULLANICILAR.Soyadi,KULLANICILAR.Adres , M.Id from MESAJLARIM M,KITAPBILGISI,KULLANICILAR where M.FromKullaniciId=KULLANICILAR.Id AND M.KitapId=KITAPBILGISI.Id AND M.ToKullaniciId="+Kullanicilar.Id+"";

        try {
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            List<String> listMesajlarim = new ArrayList<String>();

            listMesajId = new ArrayList<>();
            listAdres = new ArrayList<>();

            while (cursor.moveToNext()) {
                String s = cursor.getString(1) + " " + cursor.getString(2) + " senin " + cursor.getString(0) + " adlı kitabını ödünç vermeni istiyor.";
                listMesajlarim.add(s);
                listMesajId.add(cursor.getInt(4));
                listAdres.add(cursor.getString(3));

            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listMesajlarim);

            mesajlarim.setAdapter(arrayAdapter);

            mesajlarim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Mesajlarim.this).create();
                    alertDialog.setTitle(listAdres.get(position)+" adresine yolluyor musunuz?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Evet",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    String sql="delete from MESAJLARIM where Id="+listMesajId.get(position)+"";
                                    SQLiteDatabase sqLiteDatabase1 = database.getWritableDatabase();
                                    sqLiteDatabase1.execSQL(sql);

                                    Toast.makeText(getApplicationContext(),"İşlem Tamam",Toast.LENGTH_SHORT).show();

                                    Intent i  = new Intent(getApplicationContext(),AnaEkran.class);
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
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_SHORT).show();
        }



    }
}
