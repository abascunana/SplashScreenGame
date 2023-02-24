package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

private MotionLayout mMotionLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
    mMotionLayout = findViewById(R.id.mainlayout);

    mMotionLayout.startLayoutAnimation();

    mMotionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
        @Override
        public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

        }

        @Override
        public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

        }

        @Override
        public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            //Iniciar Menú
            openNewActivity();
            finish();
        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

        }
    });

    }
    public void openNewActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}