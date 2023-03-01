package com.example.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash.db.DbHelper;
import com.example.splash.db.DbGames;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Button sendUser;

    Spinner mSpinner;
    EditText writeUser;
    DbHelper mDbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mDbHelper = new DbHelper(LoginActivity.this);
        setContentView(R.layout.activity_login);
        sendUser = (Button) findViewById(R.id.sendUsername);
        writeUser = (EditText) findViewById(R.id.writeUser);
        mSpinner = (Spinner) findViewById(R.id.userspp);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE); /* if you want your item to be white */
                ((TextView) parent.getChildAt(0)).setTextSize(40);
                ((TextView) parent.getChildAt(0)).setTypeface(Typeface.createFromAsset(getAssets(), "chalkboard.ttf")); /* if you want your item to be white */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        loadSpinnerData();
        sendUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DbGames dBcomments = new DbGames(LoginActivity.this);

                    if (mSpinner.getSelectedItem() != null) {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("User Selection")
                                .setMessage("Do you want to insert another user or start with the one selected in the dropdown Menu?")
                                .setPositiveButton("New User", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dBcomments.insertaUser(String.valueOf(writeUser.getText()));
                                        //TODO set the new user for other activities
                                        MenuActivity.setUsername(String.valueOf(writeUser.getText()));
                                        if (!writeUser.getText().toString().isEmpty()) {
                                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast toast = Toast.makeText(LoginActivity.this, "PLEASE INTRODUCE A USER", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }

                                    }
                                })
                                .setNegativeButton("Selected User", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //TODO set the selected user for other activities
                                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                        MenuActivity.setUsername((String) mSpinner.getSelectedItem());
                                        if (!mSpinner.getSelectedItem().toString().isEmpty()) {
                                            startActivity(intent);
                                        } else {
                                            Toast toast = Toast.makeText(LoginActivity.this, "PLEASE INTRODUCE A USER", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }

                                    }
                                })
                                .show();
                    } else {
                        dBcomments.insertaUser(String.valueOf(writeUser.getText()));
                        MenuActivity.setUsername(String.valueOf(writeUser.getText()));
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadSpinnerData() {
        DbGames db = new DbGames(getApplicationContext());
        List<String> labels = db.getAllUsers();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
    }
}