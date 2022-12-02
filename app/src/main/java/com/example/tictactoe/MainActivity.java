package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - X
    // 1 - O
    // 2 - empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        ImageView gameStatus = findViewById(R.id.gameStatus);

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);


            if (activePlayer == 0) {
                // set the image of x
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                gameStatus.setImageResource(R.drawable.oplay);
              //  playerImg.setImageResource(R.drawable.oplay);
            } else {
                // set the image of o
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                gameStatus.setImageResource(R.drawable.xplay);
             //   playerImg.setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;

                gameActive = false;
                // Update the status bar for winner announcement
                if (gameState[winPosition[0]] == 0) {
                    gameStatus.setImageResource(R.drawable.xwin);
                } else {
                    gameStatus.setImageResource(R.drawable.owin);
                }


            }
        }
        if (counter == 9 && flag == 0) {
            gameStatus.setImageResource(R.drawable.nowin);
        }

        if (!gameActive) {
            Button startOverButton = (Button) findViewById(R.id.startOver);
            startOverButton.setVisibility(Button.VISIBLE);
        }
    }

    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        counter = 0;
        Button startOverButton = (Button) findViewById(R.id.startOver);
        startOverButton.setVisibility(Button.INVISIBLE);
        ImageView gameStatus = findViewById(R.id.gameStatus);
        gameStatus.setImageResource(R.drawable.empty);

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
