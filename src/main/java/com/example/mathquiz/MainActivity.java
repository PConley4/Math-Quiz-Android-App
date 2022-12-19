package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.text.TextUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int answer;
    int score;
    int problemsleft = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizStart();
    }

    protected void onResume() {
        if (problemsleft == 0){
            Intent messengerF = new Intent (this, Results.class);
            messengerF.putExtra("updatedscore", score);
            // intent can contain various things to bring over to another activity.
            startActivity(messengerF);
        }
        if (problemsleft != 0) {
            problemsleft--;
        }
        super.onResume();
        setContentView(R.layout.activity_main);
        quizStart();
}

    public void quizStart(){
        Random rand = new Random();
        int operand1 = rand.nextInt(13);
        int operand2 = rand.nextInt(13);
        int operatord = rand.nextInt(2);
        TextView problemsText = null;
        TextView problem = null;
        //everything displayed on screen is made above.

        problemsText = findViewById(R.id.numOfProblems);
        problemsText.setText(String.valueOf("There are " + (problemsleft+1) + " problems left."));
        problem = findViewById(R.id.equation);
        //linking the variable to the item in the layout itself

        if (operatord == 0){
            answer = operand1 + operand2;
            problem.setText(String.valueOf("What is: "+ operand1 + " + " + operand2 + "?"));
        }

        if (operatord == 1){
            answer = operand1 * operand2;
            problem.setText(String.valueOf("What is: "+ operand1 + " * " + operand2 + "?"));
        }

    }

    public void guessClick(View view){
        int guessing = 0;
        Intent messengerC = new Intent (this, Correct.class);
        Intent messengerI = new Intent (this, Incorrect.class);
        //here intent is used to "store" the address of another activity
        TextView guessview;

        guessview = findViewById(R.id.guess);
        guessing = Integer.parseInt(guessview.getText().toString());
        if( guessing == answer) {
            score++;
            messengerC.putExtra("updatedscore", score);
            startActivity(messengerC);
        }
        else if (guessing != answer){

            messengerI.putExtra("updatedscore", score);
            startActivity(messengerI);
        }
        else if (TextUtils.isEmpty(guessview.getText().toString())){
            Toast.makeText(getApplicationContext(), String.valueOf(answer), Toast.LENGTH_SHORT).show();
        }
    }

}