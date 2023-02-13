package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.splash.LightsOut.LightsOut;
import com.example.splash.twofoureight.Initializer;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),opciones[position],Toast.LENGTH_SHORT).show();
                if (opciones[position].equals("LightsOut")){
                    Intent intent = new Intent(MenuActivity.this, LightsOut.class);
                    startActivity(intent);
                }
                if (opciones[position].equals("2048")){
                    Intent intent = new Intent(MenuActivity.this, Initializer.class);
                    startActivity(intent);
                }
            }
        });
    }
}