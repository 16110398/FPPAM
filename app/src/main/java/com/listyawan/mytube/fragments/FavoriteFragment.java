package com.listyawan.mytube.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.listyawan.mytube.EditDataActivity;
import com.listyawan.mytube.R;
import com.listyawan.mytube.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    DatabaseHelper databaseHelper;
    private ListView listView;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        listView = (ListView) view.findViewById(R.id.listview);

        populateListView();

        return view;


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        databaseHelper= new DatabaseHelper(activity);
    }

    private void populateListView() {

        //View data
        Cursor data = databaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }

        final ListAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);


        //Edit data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = parent.getItemAtPosition(position).toString();

                Cursor data = databaseHelper.getItemID(title);
                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }

                if (itemID > -1){
                    Intent intent = new Intent(getActivity(), EditDataActivity.class);
                    intent.putExtra("id",itemID);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"Gagal",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
