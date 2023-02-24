package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.splash.db.Dbgames;

import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity {
    List<String> opciones;
    Dbgames dBgames;

    public List<String> mostrarStats(){
        opciones.add("LightsOut");
        for (int i = 0; i < dBgames.SelectLt().size(); i++) {
            opciones.add(dBgames.SelectLt().get(i));
        }
        opciones.add("2048");
        for (int i = 0; i < dBgames.SelectTf().size(); i++) {
            opciones.add(dBgames.SelectTf().get(i));
        }
        return opciones;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opciones = new ArrayList<>();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_stats);
       dBgames = new Dbgames(this);
       mostrarStats();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.list_menu,opciones);
        ListView listView = (ListView) findViewById(R.id.opciones);
        listView.setAdapter(adapter);
    }
}