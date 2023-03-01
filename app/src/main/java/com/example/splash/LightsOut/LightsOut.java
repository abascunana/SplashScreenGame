package com.example.splash.LightsOut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.splash.MenuActivity;
import com.example.splash.R;
import com.example.splash.db.DbGames;
import com.google.android.material.slider.Slider;

import java.util.Random;

public class LightsOut extends AppCompatActivity {
    private Slider mSlider;
    private int difficulty = 2;
    private int movimiento;
    private static final String KEY_INDEX = "index";

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LightsOut.username = username;
    }

    static String username;

    private boolean win;
    //MATRIZ
    private Switch[][] buttons = new Switch[5][5];
//TODO contador de tiempo

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (!win){
            final MediaPlayer mp = MediaPlayer.create(LightsOut.this, R.raw.lose);
            mp.start();
        }


        DbGames dBcomments = new DbGames(LightsOut.this);
        dBcomments.insertaRecordLt(win, movimiento, username);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("Land", "onSaveInstanceState");

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lights);
        mSlider = (Slider) findViewById(R.id.difficulty);
        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        buttons[0][0] = (Switch) findViewById(R.id.bulb0);
        buttons[0][1] = (Switch) findViewById(R.id.bulb1);
        buttons[0][2] = (Switch) findViewById(R.id.bulb2);
        buttons[0][3] = (Switch) findViewById(R.id.bulb3);
        buttons[0][4] = (Switch) findViewById(R.id.bulb4);
        buttons[1][0] = (Switch) findViewById(R.id.bulb5);
        buttons[1][1] = (Switch) findViewById(R.id.bulb6);
        buttons[1][2] = (Switch) findViewById(R.id.bulb7);
        buttons[1][3] = (Switch) findViewById(R.id.bulb8);
        buttons[1][4] = (Switch) findViewById(R.id.bulb9);
        buttons[2][0] = (Switch) findViewById(R.id.bulb10);
        buttons[2][1] = (Switch) findViewById(R.id.bulb11);
        buttons[2][2] = (Switch) findViewById(R.id.bulb12);
        buttons[2][3] = (Switch) findViewById(R.id.bulb13);
        buttons[2][4] = (Switch) findViewById(R.id.bulb14);
        buttons[3][0] = (Switch) findViewById(R.id.bulb15);
        buttons[3][1] = (Switch) findViewById(R.id.bulb16);
        buttons[3][2] = (Switch) findViewById(R.id.bulb17);
        buttons[3][3] = (Switch) findViewById(R.id.bulb18);
        buttons[3][4] = (Switch) findViewById(R.id.bulb19);
        buttons[4][0] = (Switch) findViewById(R.id.bulb20);
        buttons[4][1] = (Switch) findViewById(R.id.bulb21);
        buttons[4][2] = (Switch) findViewById(R.id.bulb22);
        buttons[4][3] = (Switch) findViewById(R.id.bulb23);
        buttons[4][4] = (Switch) findViewById(R.id.bulb24);
        randomon();


        for (int i = 0; i < buttons[0].length; i++) {
            for (int j = 0; j < buttons[1].length; j++) {
                cuadroAction(i, j, buttons);
            }

        }
        mSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                difficulty = (int) mSlider.getValue();
            }
        });
        Button button = (Button) findViewById(R.id.randobutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                randomon();
            }
        });

        Button buttonsol = (Button) findViewById(R.id.button2);
        buttonsol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(LightsOut.this)
                        .setTitle("Give Up")
                        .setMessage("¿Are you sure you want to give up?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(LightsOut.this, MenuActivity.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Nothing
                            }
                        })
                        .show();
            }
        });


    }

    public void randomon() {
        //función para empezar el juego con Switches Random encendidos, mejorar luego
        Random r = new Random();
        int limite = 0;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                boolean random = r.nextBoolean();
                if (random == true) {
                    limite++;
                }
                //TODO se podrá retocar el límite para dificultad 1-hardcore,2-difícil,3-media,4-fácil
                if (limite < (buttons.length * buttons[0].length / difficulty)) {
                    buttons[i][j].setChecked(random);
                }
                changeback(buttons[i][j]);
            }

        }

    }

    public boolean win() {
        boolean win = true;
        int i = 0;
        int j = 0;
        boolean comprobado = false;
        while (!comprobado) {

            while (i < buttons.length && !comprobado) {
                j = 0;
                while (j < buttons[0].length && !comprobado) {
                    if (buttons[i][j].isChecked() == true) {
                        win = false;
                        comprobado = true;
                    }
                    if (!comprobado) {
                        j++;
                    }
                }
                if (!comprobado) {
                    i++;
                }
            }
            comprobado = true;
        }
        return win;
    }

    public void changeback(Switch s) {
        if (s.isChecked()) {
            s.setBackgroundResource(R.drawable.squareon);
        } else {
            s.setBackgroundResource(R.drawable.squareoff);
        }
    }

    public void surround(int w, int h) {
        if (w > 0) {
            buttons[h][w - 1].setChecked(!buttons[h][w - 1].isChecked());
            changeback(buttons[h][w - 1]);
        }
        if (h > 0) {
            buttons[h - 1][w].setChecked(!buttons[h - 1][w].isChecked());
            changeback(buttons[h - 1][w]);
        }
        if (h < buttons.length - 1) {
            buttons[h + 1][w].setChecked(!buttons[h + 1][w].isChecked());
            changeback(buttons[h + 1][w]);
        }
    }

    public void cuadroAction(int h, int w, Switch[][] buttons) {

        buttons[h][w].setOnClickListener(v -> {

            if (buttons[h][w].isChecked()) {
                //establecer límites para que la app deje de crashear
                surround(w, h);
                for (int i = 0; i < 2; i++) {

                    if (w < buttons.length - 1) {
                        buttons[h][w + i].setChecked(!buttons[h][w + 1].isChecked());
                        changeback(buttons[h][w + i]);
                    }
                    buttons[h][w].setChecked(true);
                    changeback(buttons[h][w]);
                }
            } else {
                surround(w, h);
                for (int i = 0; i < 2; i++) {
                    if (w < buttons.length - 1) {
                        buttons[h][w + i].setChecked(!buttons[h][w + 1].isChecked());
                        changeback(buttons[h][w + i]);
                    }
                    buttons[h][w].setChecked(false);
                    changeback(buttons[h][w]);
                }
            }
            movimiento++;
            if (win()) {
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.win);
                mp.start();
                win = true;
                Toast toast = Toast.makeText(this, "Has ganado :DDDDDDDD", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

            }
        });
    }
}






