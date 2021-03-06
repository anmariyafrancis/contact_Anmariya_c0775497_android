package com.example.contact_anmariya_c0775497_android;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewContacts extends AppCompatActivity {

    DataBaseHelper mDatabase;
    EditText serchText;
    List<Person> personList;

    ListView listView;
    PersonAdapter personAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        mDatabase = new DataBaseHelper(this);
        listView = findViewById(R.id.lvUser);
        serchText = findViewById(R.id.searchView);
        personList = new ArrayList<>();
        loadData();

        personAdpter = new PersonAdapter(this,R.layout.cell_contact,personList,mDatabase);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(personAdpter);

        serchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String text = serchText.getText().toString();
                (personAdpter).filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void loadData() {


        Cursor cursor = mDatabase.getAllData();
        if (cursor.moveToFirst()){

            do {
                personList.add(new Person(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));


            }while (cursor.moveToNext());

            cursor.close();
        }
    }
}