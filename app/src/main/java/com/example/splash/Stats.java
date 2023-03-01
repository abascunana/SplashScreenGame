package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.splash.db.DbGames;

import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity {
    List<String> opciones;
    DbGames dBgames;


    static String username;

    public static void setUsername(String username) {
        Stats.username = username;
    }

    public List<String> mostrarStats() {
        opciones.add("LightsOut");
        for (int i = 0; i < dBgames.SelectLt(username).size(); i++) {
            opciones.add(dBgames.SelectLt(username).get(i));
        }
        opciones.add("2048");
        for (int i = 0; i < dBgames.SelectTf(username).size(); i++) {
            opciones.add(dBgames.SelectTf(username).get(i));
        }
        return opciones;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opciones = new ArrayList<>();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_stats);
        dBgames = new DbGames(this);
        try {
            mostrarStats();
        } catch (Exception e) {

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.stats_item, R.id.list_menu, opciones);
        ListView listView = (ListView) findViewById(R.id.opciones);
        listView.setAdapter(adapter);
    }
}