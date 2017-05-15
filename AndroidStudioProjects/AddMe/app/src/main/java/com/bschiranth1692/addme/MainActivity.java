package com.bschiranth1692.addme;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView,answer,textViewRight,textViewLeft;
    ArrayList<Integer> answers;
    int indexOfAnswer,incorrectAnswer;
    Button b0,b1,b2,b3,again;

    int score = 0,numberOfQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list
        answers = new ArrayList<>();

        //
        answer = (TextView) findViewById(R.id.answer);
        textViewLeft = (TextView) findViewById(R.id.textViewLeft);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        again = (Button) findViewById(R.id.again);

        generateQuestions();
        again(findViewById(R.id.again));
    }

    public void choose(View view){

        answer.setVisibility(View.VISIBLE);

        if(view.getTag().toString().equals(Integer.toString(indexOfAnswer))) {
            answer.setText("Correct!");
            score++;
        }
        else {
            answer.setText("Incorrect!");
        }
        numberOfQuestions++;
        textViewRight.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();

    }

    public void generateQuestions(){

        //clear answers before calling
        answers.clear();

        //random
        Random random = new Random();

        int a = random.nextInt(51);
        int b = random.nextInt(51);
        indexOfAnswer = random.nextInt(4);

        textView = (TextView) findViewById(R.id.textViewMiddle);
        textView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        for (int i = 0 ; i < 4; i++){
            if (i == indexOfAnswer) {
                answers.add(a+b);
            }else {
                incorrectAnswer = random.nextInt(101);
                while (incorrectAnswer == (a+b)) incorrectAnswer = random.nextInt(101);
                answers.add(incorrectAnswer);

            }
        }

        b0 = (Button) findViewById(R.id.button1);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);

        b0.setText(Integer.toString(answers.get(0)));
        b1.setText(Integer.toString(answers.get(1)));
        b2.setText(Integer.toString(answers.get(2)));
        b3.setText(Integer.toString(answers.get(3)));
    }




    public void again(View view){
        score = 0;
        numberOfQuestions = 0;
        textViewLeft.setText("30s");
        textViewRight.setText("0/0");
        answer.setVisibility(View.INVISIBLE);
        again.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewLeft.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                textViewLeft.setText("0s");
                answer.setText("Done! Your Score :"+score+"/"+numberOfQuestions);
                again.setVisibility(View.VISIBLE);

            }
        }.start();
    }
}
