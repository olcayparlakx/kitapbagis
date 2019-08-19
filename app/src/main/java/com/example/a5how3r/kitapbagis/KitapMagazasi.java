package com.example.a5how3r.kitapbagis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class KitapMagazasi extends AppCompatActivity {

    List<String> listKitapAdi;
    List<Integer> listKitapId;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_magazasi);

        listView = findViewById(R.id.listview);
        listKitapAdi = new ArrayList<>();
        listKitapId = new ArrayList<>();

        String sql = "select * from KITAPBILGISI where KullaniciId!="+Kullanicilar.Id+" and AlindiMi=0 order by KitapAdi";
        Database database = new Database(getApplicationContext());

        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        while (cursor.moveToNext())
        {
            listKitapAdi.add(cursor.getString(1));
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int Id =listKitapId.get(i);
                Intent intent = new Intent(getApplicationContext(),KitapBilgileri.class);
                intent.putExtra("KitapId",Id);
                startActivity(intent);

            }
        });







    }
}
