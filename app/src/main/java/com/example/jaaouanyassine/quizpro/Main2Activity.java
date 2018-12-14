package com.example.jaaouanyassine.quizpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Basedonneequiz myDb;
    TextView highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button buttonStartQuiz =findViewById(R.id.button_start_quiz);
        myDb = new Basedonneequiz(this);
        myDb.open();
        highScore = (TextView) findViewById(R.id.text_view_highscore);
        highScore.setText("High Score: "+myDb.getHighScore());
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz(){
        Intent intent = new Intent(Main2Activity.this,Question_1.class);
        startActivity(intent);

    }
}
