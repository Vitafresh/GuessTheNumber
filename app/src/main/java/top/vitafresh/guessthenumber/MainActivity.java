package top.vitafresh.guessthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    long randomNum;  //Randomly generated number to guess
    long tries = 0;      //Current try
    long maxTries;   //Maximum number of tries
    boolean startNewGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //newGame(20);
    }

    public void onGuessClick(View v){
        //Guess button clicked
        TextView txtNumber = findViewById(R.id.txtNumber);
        TextView txtGeneratedNum = findViewById(R.id.txtGeneratedNum);
        TextView txtTries = findViewById(R.id.txtTries);
        Button btnGuess = findViewById(R.id.btnGuess);

        if (startNewGame) {
            startNewGame = false;
            newGame(20);
            btnGuess.setText("Guess");
        }
        tries++;    //Current number of tries
        txtTries.setText("Try " + String.valueOf(tries) + " of " + String.valueOf(maxTries));

        Log.d("TestApp", "Generated: " + randomNum);

        txtGeneratedNum.setText(String.valueOf(randomNum));

        int num = Integer.parseInt(txtNumber.getText().toString());
        if (num > randomNum) {
            highNumberMessage(num);
        } else if (num < randomNum) {
            lowNumberMessage(num);
        } else {
            Toast.makeText(this, "You are guessed the number!", Toast.LENGTH_SHORT).show();
            startNewGame = true;
            btnGuess.setText("Start New");
        }
    }

    public void lowNumberMessage(long num){
        //Make sound, change image, show message, etc.
        Toast.makeText(this, "Your guess " + num + " is too low", Toast.LENGTH_LONG).show();
    }

    public void highNumberMessage(long num){
        //Make sound, change image, show message, etc.
        Toast.makeText(this, "Your guess " + num + " is too high", Toast.LENGTH_LONG).show();
    }

    public long getMaxTries(int guesedNumber){
        //log a (b) = log c (b) / log c (a)
        long n;
        //Логарифм числа по основанию 2
        n = (long)(Math.log(guesedNumber)/Math.log(2));
        return n + 1;
    }

    public void newGame(int maxNumber){
        Random rnd = new Random();
        randomNum = rnd.nextInt(maxNumber);
        tries = 0;
        maxTries = getMaxTries(maxNumber)+1;
    }
}
