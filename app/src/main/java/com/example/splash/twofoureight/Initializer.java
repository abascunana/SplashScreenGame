package com.example.splash.twofoureight;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.example.splash.LightsOut.LightsOut;
import com.example.splash.MenuActivity;
import com.example.splash.R;
import com.example.splash.db.DbGames;

public class Initializer extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "MainActivity";

    private TextView[][] mTableroVisual;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Initializer.username = username;
    }

    static String username;

    private Juego mJuego;

    //TODO contador de tiempo
    private boolean win;

    private int movimiento;
    private GestureDetectorCompat mDetector;
    private final int UMBRAL_VELOCIDAD = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2048);
        getSupportActionBar().hide();
        mJuego = new Juego(this);
        mTableroVisual = new TextView[Juego.TAB][Juego.TAB];
        mDetector = new GestureDetectorCompat(this, this);


        inicializarComponentes();
        actualizarTablero();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (mDetector.onTouchEvent(e)) {
            return true;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY) {
        movimiento(vX, vY);
        return true;
    }

    boolean mMovimiento;

    private void movimiento(float vX, float vY) {
        boolean hayMovimiento = false;

        if (Math.abs(vX) > Math.abs(vY)) {
            if (vX > UMBRAL_VELOCIDAD)
                hayMovimiento = mJuego.moverHaciaDerecha();
            else if (vX < -UMBRAL_VELOCIDAD)
                hayMovimiento = mJuego.moverHaciaIzquierda();
        } else {
            if (vY > UMBRAL_VELOCIDAD)
                hayMovimiento = mJuego.moverHaciaAbajo();
            else if (vY < -UMBRAL_VELOCIDAD)
                hayMovimiento = mJuego.moverHaciaArriba();
        }

        if (!mJuego.estaTableroLleno()) {
            if (hayMovimiento) {
                if (!mMovimiento)
                    mJuego.conseguirSiguientePieza();
                actualizarTablero();
                mMovimiento = true;
            }
        }
        movimiento++;

    }

    private void inicializarComponentes() {
        mTableroVisual[0][0] = findViewById(R.id.cuadro0);
        mTableroVisual[0][1] = findViewById(R.id.cuadro1);
        mTableroVisual[0][2] = findViewById(R.id.cuadro2);
        mTableroVisual[0][3] = findViewById(R.id.cuadro3);

        mTableroVisual[1][0] = findViewById(R.id.cuadro4);
        mTableroVisual[1][1] = findViewById(R.id.cuadro5);
        mTableroVisual[1][2] = findViewById(R.id.cuadro6);
        mTableroVisual[1][3] = findViewById(R.id.cuadro7);

        mTableroVisual[2][0] = findViewById(R.id.cuadro8);
        mTableroVisual[2][1] = findViewById(R.id.cuadro9);
        mTableroVisual[2][2] = findViewById(R.id.cuadro10);
        mTableroVisual[2][3] = findViewById(R.id.cuadro11);

        mTableroVisual[3][0] = findViewById(R.id.cuadro12);
        mTableroVisual[3][1] = findViewById(R.id.cuadro13);
        mTableroVisual[3][2] = findViewById(R.id.cuadro14);
        mTableroVisual[3][3] = findViewById(R.id.cuadro15);
        Button buttonsol = (Button) findViewById(R.id.button2);
        buttonsol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(Initializer.this)
                        .setTitle("Give Up")
                        .setMessage("¿Are you sure you want to give up?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Initializer.this, MenuActivity.class);
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

    @Override
    protected void onPause() {
        if (!win){
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.lose);
            mp.start();
        }
        DbGames dBcomments = new DbGames(Initializer.this);
        dBcomments.insertaRecordTf(win, movimiento, username);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void actualizarTablero() {
        int[][] tablero = mJuego.getTablero();
        for (int i = 0; i < Juego.TAB; ++i) {
            for (int j = 0; j < Juego.TAB; ++j) {
                int valorCasilla = tablero[i][j];
                if (valorCasilla == 0) {
                    mTableroVisual[i][j].setTextColor(0);
                } else {
                    mTableroVisual[i][j].setTextColor(0xFF000000);
                }
                mTableroVisual[i][j].setText(String.valueOf(valorCasilla));
            }
        }
    }


    public void win() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.win);
        mp.start();
        win = true;
        Toast toast = Toast.makeText(this, "Has ganado :DDDDDDDD", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
