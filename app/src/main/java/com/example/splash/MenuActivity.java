package com.example.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.splash.LightsOut.LightsOut;
import com.example.splash.Tutorial.Tutorial2048;
import com.example.splash.db.DbHelper;
import com.example.splash.twofoureight.Initializer;

public class MenuActivity extends AppCompatActivity {
    DbHelper mDbHelper;
    String[] opciones = {"LightsOut","2048","Tutorial","See Stats","New Stats"};
    private void crearbase(){
        DbHelper dbHelper = mDbHelper;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        if (db != null){
            Log.println(Log.ASSERT,"mensaje","Base Creada");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(getResources().getString(R.string.menu));
        getSupportActionBar().hide();
        mDbHelper =  new DbHelper(MenuActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.menu_item, R.id.list_menu,opciones);
        ListView listView = (ListView) findViewById(R.id.opciones);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (opciones[position].equals("LightsOut")){
                    Intent intent = new Intent(MenuActivity.this, LightsOut.class);
                    startActivity(intent);
                }
                if (opciones[position].equals("2048")){
                    Intent intent = new Intent(MenuActivity.this, Initializer.class);
                    startActivity(intent);
                }
                if (opciones[position].equals("Tutorial")){
                    Intent intent = new Intent(MenuActivity.this, Tutorial2048.class);
                    startActivity(intent);
                }
                if (opciones[position].equals("See Stats")){
                    Intent intent = new Intent(MenuActivity.this, Stats.class);
                    startActivity(intent);
                }
                if (opciones[position].equals("New Stats")){
                    new AlertDialog.Builder(MenuActivity.this)
                            .setTitle("Delete entry")
                            .setMessage("¿Do you wish to restart the game statistics? This action will delete previous statistics and start recompiling new ones. If there aren't previous statistics, this action will start recompiling game data.")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mDbHelper.deleteAll();
                                    Toast toast = Toast.makeText(MenuActivity.this, "Datos reiniciados", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Log.println(Log.ASSERT,"mensaje","Base Borrada");
                                    crearbase();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                //Nothing
                                }
                            })
                            .show();


                }
            }
        });
    }
}