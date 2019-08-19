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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Kitaplarim extends AppCompatActivity {

    List<String> listKitapAdi;
    List<Integer> listKitapId,listKitaplarımId;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitaplarim);

        listView = findViewById(R.id.listview);
        listKitapAdi = new ArrayList<>();
        listKitapId = new ArrayList<>();
        listKitaplarımId = new ArrayList<>();

        try {
            String sql = "select K.KitapId,AldigimGun,KM.KitapAdi,K.Id from KITAPLARIM K,KITAPBILGISI KM where K.KullaniciId=" + Kullanicilar.Id + " AND K.KitapId=KM.Id ";

            final Database database = new Database(getApplicationContext());

            SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                listKitaplarımId.add(cursor.getInt(3));
                Calendar calendar = Calendar.getInstance();
            int yil = calendar.get(Calendar.YEAR);
            int ay = calendar.get(Calendar.MONTH) + 1;
            int gun = calendar.get(Calendar.DAY_OF_MONTH);
            double suanGunSayisi = yil * 365 + ay * 30 + gun+30;

            String sAlinanGunSayisi = cursor.getString(1);
            String[] dAlinanGunSayisi = sAlinanGunSayisi.split("-");

            int aYil = Integer.parseInt(dAlinanGunSayisi[2]);
            int aAy = Integer.parseInt(dAlinanGunSayisi[1]);
            int aGun = Integer.parseInt(dAlinanGunSayisi[0]);

            double alinanGunSayisi = aYil * 365 + aAy * 30 + aGun;

            String gunsayisi = "";
            if ((suanGunSayisi - alinanGunSayisi) > 30)
                gunsayisi = "Teslim Tarihi Geçti";
            else
                gunsayisi = "Teslim Etmenize " + (suanGunSayisi - alinanGunSayisi) + " gün kaldı";


            listKitapAdi.add(cursor.getString(2) + "  " + gunsayisi);
            listKitapId.add(cursor.getInt(0));

        }
            ArrayList<ListViewList> list = new ArrayList<>();
            for(int i=0;i<listKitapId.size();i++)
            {
                ListViewList listViewList = new ListViewList(listKitapAdi.get(i));
                list.add(listViewList);
            }

            ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(),R.layout.liste_item,list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Kitaplarim.this).create();
                    alertDialog.setTitle("Teslim Ettiniz Mi?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Evet",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    int kitaplarimId =  listKitaplarımId.get(position);

                                    String sql="delete from KITAPLARIM where Id="+kitaplarimId+"";
                                    SQLiteDatabase sqLiteDatabase1 = database.getWritableDatabase();
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

        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex+"",Toast.LENGTH_SHORT).show();
        }

    }
}
