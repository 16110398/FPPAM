package com.listyawan.mytube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.listyawan.mytube.db.DatabaseHelper;
import com.listyawan.mytube.fragments.FavoriteFragment;

/**
 * Created by User on 09-Jan-19.
 */

public class EditDataActivity extends AppCompatActivity{

    private Button btnSave , btnDelete;
    private EditText editTitle;

    DatabaseHelper databaseHelper;

    private String selectedTitle;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button) findViewById(R.id.btnSave);
        editTitle = (EditText) findViewById(R.id.edit_title);

        databaseHelper = new DatabaseHelper(this);

        //mengambil data dari put extra
        Intent intent = getIntent();
        selectedID = intent.getIntExtra("id",-1);
        selectedTitle = intent.getStringExtra("title");

        editTitle.setText(selectedTitle);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString();

                if (!title.equals(" ")){
                    databaseHelper.updateTitle(title,selectedID,selectedTitle);
                    Toast.makeText(EditDataActivity.this,"Data berhasil diubah",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(EditDataActivity.this,"Gagal",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper.deleteTitle(selectedID,selectedTitle);
                editTitle.setText("");
                Toast.makeText(EditDataActivity.this,selectedTitle+" Dihapus dari favorit",Toast.LENGTH_LONG).show();

            }
        });

    }

}
