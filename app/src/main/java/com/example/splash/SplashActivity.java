package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
private MotionLayout mMotionLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            System.out.println("Completado");
        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

        }
    });
    }
}