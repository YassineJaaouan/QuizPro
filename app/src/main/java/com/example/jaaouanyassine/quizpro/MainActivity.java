package com.example.jaaouanyassine.quizpro;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    private TextView tv_Logo;
    Basedonneequiz bd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Logo = findViewById(R.id.tv_Logo);
        bd = new Basedonneequiz(this);
        bd.open();

        if (!bd.TableExist()) {
            bd.initScore();
            ClassQuestion q1 = new ClassQuestion(1, "what is the capital of Tunisia ? ", "Ben Arous", "Tunis",
                    "Ariana", "Tunis");
            bd.addQuestion(q1);
            ClassQuestion q2 = new ClassQuestion(2, "what is the capital of USA ? ", "New York", "California",
                    "Washenton DC", "Washenton DC");
            bd.addQuestion(q2);
            ClassQuestion q3 = new ClassQuestion(3, "what is the capital of France ? ", "Paris", "Lyon",
                    "Marseille", "Paris");
            bd.addQuestion(q3);
            ClassQuestion q4 = new ClassQuestion(4, "what is the capital of Italy ? ", "Roma", "Milan",
                    "Napoli", "Roma");
            bd.addQuestion(q4);
            ClassQuestion q5 = new ClassQuestion(5, "what is the capital of Egypt ? ", "Charm Chikh", "Kairo",
                    "Giza", "Kairo");
            bd.addQuestion(q5);
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        }, 3000);
        manageBlinkEffect();
    }
    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(tv_Logo, "backgroundColor", Color.WHITE, Color.RED,
                Color.WHITE);
        anim.setDuration(2000);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }
}