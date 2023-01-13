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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opciones);
        ListView listView = (ListView) findViewById(R.id.opciones);
        listView.setBackgroundColor(Color.WHITE);
        listView.setAdapter(adapter);
    }
}