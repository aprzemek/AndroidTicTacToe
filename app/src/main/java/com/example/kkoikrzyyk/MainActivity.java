package com.example.kkoikrzyyk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 - zółty, 1- czerwony, 2- brak koloru

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            Log.i("Stan", gameState.toString());

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Żółty";
                    } else {
                        winner = "Czerwony";
                    }
                    // ktoś wygrał
                  //  Toast.makeText(this, winner + " wygrał", Toast.LENGTH_LONG).show();

                    gameActive = false;
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    winnerTextView.setText(winner +" Wygrał");
                    GridLayout gridLayout = findViewById(R.id.gridLayout);
                    gridLayout.setVisibility(View.INVISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);


                }

            }
        }
    }

    public void playAgain(View view){
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);

        }
        activePlayer = 0;
        gameActive = true;
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

    }



}