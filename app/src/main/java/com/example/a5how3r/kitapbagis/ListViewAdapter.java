package com.example.a5how3r.kitapbagis;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter  extends ArrayAdapter<ListViewList> {
    private static final String TAG = "ListViewAdapter";

    private Context mContext;
    int mResource;
    /**
     * Default constructor for the KendiIlaninListAdapter
     * @param context
     * @param resource
     * @param objects
     */

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<ListViewList> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String KitapAdi =getItem(position).getKitapAdi();




        ListViewList list = new ListViewList(KitapAdi);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);



        TextView txtKitapAdi = convertView.findViewById(R.id.txtKitapAdi);

        txtKitapAdi.setText(KitapAdi);




        return convertView;
    }
}
