package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean croos_move = true;
    private int[][] matrix = new int[3][3];
    private boolean check_all_filled(){
        boolean filled = true;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j) {
                if(matrix[i][j]==0)
                    filled = false;
            }
        }
        return filled;
    }
    private boolean check_win(){
        boolean win = false;
        for(int i=0;i<3;++i){
            if(matrix[i][0]==matrix[i][1] && matrix[i][1]==matrix[i][2] && matrix[i][1]!=0){
                win = true;
            }
        }
        for(int i=0;i<3;++i){
            if(matrix[0][i]==matrix[1][i] && matrix[1][i]==matrix[2][i] && matrix[1][i]!=0){
                win = true;
            }
        }
        if(matrix[0][0]==matrix[1][1] && matrix[1][1]==matrix[2][2] && matrix[1][1]!= 0) {
            win = true;
        }
        if(matrix[0][2]==matrix[1][1] && matrix[1][1]==matrix[2][0] && matrix[1][1]!= 0) {
            win = true;
        }

        return win;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sign(View v) {
        Button b = (Button) v;
        int number = Integer.parseInt(v.getTag().toString());
        if (croos_move) {
            b.setText("x");
            matrix[(number-1)/3][(number-1)%3] = 1;
        } else {
            b.setText("o");
            matrix[(number-1)/3][(number-1)%3] = 2;
        }
        if (check_win() || check_all_filled()) {
            TextView winning_text = (TextView) findViewById(R.id.winning_text);
            winning_text.setVisibility(View.VISIBLE);
            String text;
            if (check_win()) {
                if (croos_move) {
                    text = "Congratulations! x wins!";
                } else {
                    text = "Congratulations! o wins!";
                }
                int [] ids = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
                        R.id.button6, R.id.button7, R.id.button8, R.id.button9};
                for (int id : ids) {
                    Button button = (Button) findViewById(id);
                    button.setEnabled(false);
                }
            }
            else
                text = "Oops! It's a draw!";
            winning_text.setText(text);
            Button restart_button = (Button) findViewById(R.id.button11);
            restart_button.setEnabled(true);
            restart_button.setVisibility(View.VISIBLE);
        }
        croos_move = !croos_move;
        b.setEnabled(false);
    }
    public void restart(View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    public void exit(View v){
        finish();
        System.exit(0);
    }
}