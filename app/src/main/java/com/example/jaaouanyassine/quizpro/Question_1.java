package com.example.jaaouanyassine.quizpro;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Locale;

public class Question_1 extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    Basedonneequiz myDb ;
    TextView question;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    TextView score;
    TextView NumQ;
    Button confirmer;
    RadioGroup radioGroup;
    ClassQuestion q;
   private ColorStateList textColorDefaultCd ;
   private CountDownTimer countDownTimer;
   private long timeLeftInMillis;
   private TextView textViewCountDown;

    int qc = 1;
    int s=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_1);
        question = (TextView) findViewById(R.id.text_view_question);
        r1 = (RadioButton) findViewById(R.id.radio_button_option1);
        r2 = (RadioButton) findViewById(R.id.radio_button_option2);
        r3 = (RadioButton) findViewById(R.id.radio_button_option3);
        score = (TextView) findViewById(R.id.text_view_score);
        NumQ = (TextView) findViewById(R.id.text_view_question_count);
        confirmer = (Button) findViewById(R.id.button_confirm_next);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        textViewCountDown = (TextView) findViewById(R.id.text_view_countdown);
        myDb = new Basedonneequiz(this);
        myDb.open();
        q = myDb.getQuestionQuiz(qc);
        question.setText(q.getQuestion());
        r1.setText(q.getAnser1());
        r2.setText(q.getAnser2());
        r3.setText(q.getAnser3());

        textColorDefaultCd= textViewCountDown.getTextColors();


        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                StartCountDown();
                String reponse="";
                if(r1.isChecked()) {reponse= r1.getText().toString();}
                if(r2.isChecked()) {reponse= r2.getText().toString();}
                if(r3.isChecked()) {reponse= r3.getText().toString();}

                if(reponse.equals(q.getCorrect()))
                {
                    s++;
                    if (s>myDb.getHighScore()) myDb.addScore(s);
                    Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_SHORT).show();
                    radioGroup.clearCheck();
                    qc++;
                    if(qc<=5)
                    {
                        q = myDb.getQuestionQuiz(qc);
                        NumQ.setText("Question : "+qc+"/5");
                        score.setText("Score: "+s);
                        question.setText(q.getQuestion());
                        r1.setText(q.getAnser1());
                        r2.setText(q.getAnser2());
                        r3.setText(q.getAnser3());
                    }
                    else
                    {
                        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                        intent.putExtra("msg","Great end Game");
                        startActivity(intent);
                    }
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("msg","Game Over");
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Game over",Toast.LENGTH_SHORT).show();
                }

            }
        });  timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        StartCountDown();


    }
                private void StartCountDown(){
                countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeftInMillis = millisUntilFinished;
                        updateCountDownText();
                    }

                    @Override
                    public void onFinish() {
                     timeLeftInMillis =0;
                     updateCountDownText();
                    }
                }.start();
                }
                private void updateCountDownText(){
                    int minutes = (int) (timeLeftInMillis /1000) / 60 ;
                    int seconds = (int)(timeLeftInMillis /1000) %60;
                    String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes ,seconds);
                    textViewCountDown.setText(timeFormatted);
                    if(timeLeftInMillis < 10000){
                        textViewCountDown.setTextColor(Color.RED);
                    }else{
                        textViewCountDown.setTextColor(textColorDefaultCd);
                    }
                }

  //  @Override
 //   protected void onDestroy() {
     //   super.onDestroy();
      //  if (countDownTimer != null){
       //     countDownTimer.cancel();
      //  }

   // }
}
