package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {
    String[] opciones = {"LightsOut","2048"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(getResources().getString(R.string.menu));
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.menu_item, R.id.list_menu,opciones);
        ListView listView = (ListView) findViewById(R.id.opciones);
        listView.setAdapter(adapter);
    }
}