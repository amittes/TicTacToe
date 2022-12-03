package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    // 0 - X
    // 1 - O
    // 2 - empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    HashMap<String, int[]> wins = new HashMap<>();
    public static int counter = 0;

    public MainActivity () {
        wins.put("mark1", new int[]{0, 4, 8}); // rightDiagonal
        wins.put("mark2", new int[]{2, 4, 6}); // leftDiagonal
        wins.put("mark3", new int[]{0, 3, 6}); // leftColumn
        wins.put("mark4", new int[]{1, 4, 7}); // centerColumn
        wins.put("mark5", new int[]{2, 5, 8}); // rightColumn
        wins.put("mark6", new int[]{0, 1, 2}); // firstRow
        wins.put("mark7", new int[]{3, 4, 5}); // secondRow
        wins.put("mark8", new int[]{6, 7, 8}); // ThirdRow
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        ImageView gameStatus = findViewById(R.id.gameStatus);

        if (gameActive && gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);


            if (activePlayer == 0) { // set the image of x
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                gameStatus.setImageResource(R.drawable.oplay);
            } else { // set the image of o
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                gameStatus.setImageResource(R.drawable.xplay);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        boolean isWinner = false;
        // create an object of Iterator
        Iterator<Map.Entry<String, int[]>> iterate1 = wins.entrySet().iterator();
        while(iterate1.hasNext() && !isWinner) {
            Map.Entry<String, int[]> curr = iterate1.next();
            int[] winPosition = curr.getValue();
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                gameActive = false;
                isWinner = true;

                ImageView winPositionLayout = findViewById(R.id.winPosition);
                int drawableId = getResources().getIdentifier(curr.getKey(), "drawable", getPackageName());
                winPositionLayout.setImageResource(drawableId);

                // Update the status bar for winner announcement
                if (gameState[winPosition[0]] == 0) {
                    gameStatus.setImageResource(R.drawable.xwin);
                } else {
                    gameStatus.setImageResource(R.drawable.owin);
                }
            }
        }

        if (counter == 9 && !isWinner) {
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
        Button startOverButton = findViewById(R.id.startOver);
        startOverButton.setVisibility(Button.INVISIBLE);
        ImageView gameStatus = findViewById(R.id.gameStatus);
        gameStatus.setImageResource(R.drawable.empty);
        ImageView winPositionLayout = findViewById(R.id.winPosition);
        winPositionLayout.setImageResource(R.drawable.empty);

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
