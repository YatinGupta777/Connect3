package com.yatin.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = black 1 = red

     boolean gameIsActive = true;

    //2 means unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPostions ={ {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view){

        ImageView counter = (ImageView) view;
        int tappedCounter =  Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]== 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.black);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                activePlayer++;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
                activePlayer--;
            }

            for(int []winningPostion :winningPostions ){

                if(gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                        gameState[winningPostion[1]] == gameState[winningPostion[2]] &&
                        gameState[winningPostion[0]] !=2) {

//someone won
                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[winningPostion[0]] == 0)
                        winner = "Black";
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won !");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);

                }else{

                    boolean gameIsOver = true;

                    for(int counterState : gameState){
                            if(counterState == 2) gameIsOver = false;
                    }

                    if(gameIsOver)
                    {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("DRAW");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);
                    }


                }

            }

        }


    }

      public void playAgain(View view){

          gameIsActive = true;


          LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

          layout.setVisibility(View.INVISIBLE);

          activePlayer = 0;

          for(int i = 0 ; i < gameState.length;i++){

                gameState[i] = 2;
          }

          GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

          for(int i = 0 ;i < gridLayout.getChildCount();i++){

              ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);


          }


      }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}
}
