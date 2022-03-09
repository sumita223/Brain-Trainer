package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    TextView resultTextView;
    int score=0;
    int numberOfQuestions;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        resultTextView.setText("");
        new CountDownTimer(12000,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText(Integer.toString(score) + " correct out of " + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
            }
        }.start();
    }


    public void chooseCorrectAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){   //correctans==tag which is clicked
            //resultTextView.setText("Correct!");
            score++;
        }else {
            //resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }
    public void newQuestion(){

        Random rand = new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" +Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();

        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer)
                answer.add(a+b);
            else{
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer==a+b)
                    wrongAnswer = rand.nextInt(41);  //new wrong ans
                answer.add(wrongAnswer);
            }
        }
        //Answer is alloted to thr buttons
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
         sumTextView = findViewById(R.id.sumTextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.pLayAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}